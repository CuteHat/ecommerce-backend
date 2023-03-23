package com.example.order.service.impl;

import com.example.order.model.*;
import com.example.order.persistence.entity.OrderEntity;
import com.example.order.productapi.DecrementStockDto;
import com.example.order.productapi.ProductApiService;
import com.example.order.productapi.ProductDto;
import com.example.order.service.*;
import com.example.order.util.HandledExceptionFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceFacadeImpl implements OrderServiceFacade {
    private final OrderService orderService;
    private final NotificationQueueService notificationQueueService;
    private final NotificationService notificationService;
    private final PadQueueService padQueueService;
    private final ProductQueueService productQueueService;
    private final ProductApiService productApiService;

    @Transactional
    public void createOrder(OrderCreateRequest request) {
        Long customerId = 1L; // TODO change with real customer id

        // validate stock
        checkStock(request);

        // fetch products from product API
        List<ProductDto> products = getProducts(request);
        Map<Long, ProductDto> productsMap = products
                .stream()
                .collect(Collectors.toMap(ProductDto::getId, product -> product));

        // persist order to database
        OrderEntity order = persistOrder(request, customerId, productsMap);

        // send order to product queue, decrement product stock
        productQueueService.decrementStock(request.getOrderItems());

        // send order to pad queue, create delivery
        PadOrderCreateRequest padOrderCreateRequest = new PadOrderCreateRequest(
                order.getId(),
                order.getTotalPrice(),
                customerId,
                order.getOrderItems().stream().map(OrderItemDto::transform).toList());

        padQueueService.createPendingDelivery(padOrderCreateRequest);

        // send notifications to sellers and client
        List<NotificationDto> notifications = generateNotifications(request, products, order);
        notificationQueueService.sendMessage(notifications);

    }

    private List<NotificationDto> generateNotifications(OrderCreateRequest request, List<ProductDto> products, OrderEntity order) {
        List<NotificationDto> notificationDtos = new ArrayList<>(notificationService.createSellerNotifications(request.getOrderItems(), products));
        notificationDtos.add(notificationService.createClientNotifications(order.getId(), order.getTotalPrice(), request.getCustomerPhone()));

        return notificationDtos;
    }


    private OrderEntity persistOrder(OrderCreateRequest request, Long customerId, Map<Long, ProductDto> productsMap) {
        List<ProductDtoWrapper> productDtoWrappers = request.getOrderItems().stream().map(orderItem -> {
            ProductDto product = productsMap.get(orderItem.getProductId());
            return new ProductDtoWrapper(product, orderItem.getQuantity());
        }).toList();

        return orderService.persistOrder(productDtoWrappers, customerId);
    }

    private List<ProductDto> getProducts(OrderCreateRequest request) {
        List<Long> productIds = request.getOrderItems().stream().map(OrderItem::getProductId).collect(Collectors.toList());
        return productApiService.filter(productIds);
    }

    private void checkStock(OrderCreateRequest request) {
        List<DecrementStockDto> decrementStockDtos = request.getOrderItems().stream()
                .map(DecrementStockDto::transform)
                .toList();

        if (!productApiService.stockAvailable(decrementStockDtos))
            throw HandledExceptionFactory.getHandledException("stock not available");
    }
}
