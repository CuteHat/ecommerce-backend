package com.example.pad.service.impl;

import com.example.pad.model.OrderCreateRequest;
import com.example.pad.model.OrderDto;
import com.example.pad.model.OrderItem;
import com.example.pad.model.ProductDtoWrapper;
import com.example.pad.persistence.entity.CourierEntity;
import com.example.pad.persistence.entity.OrderEntity;
import com.example.pad.productapi.ProductApiService;
import com.example.pad.productapi.ProductDto;
import com.example.pad.service.CourierService;
import com.example.pad.service.DeliveryService;
import com.example.pad.service.OrderService;
import com.example.pad.service.OrderServiceFacade;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceFacadeImpl implements OrderServiceFacade {
    private final ProductApiService productAPI;
    private final OrderService orderService;
    private final DeliveryService deliveryService;
    private final CourierService courierService;

    /**
     * Creates a new pending order by fetching products from the product API, persisting the order to the database,
     * and creating a delivery with the least busy courier.
     *
     * @param request the request containing order items and customer id
     */
    @Override
    @Transactional
    public void createPending(OrderCreateRequest request) {
        // fetch products from product API
        List<Long> productIds = request.getOrderItems().stream().map(OrderItem::getProductId).collect(Collectors.toList());
        Map<Long, ProductDto> productsMap = productAPI
                .filter(productIds)
                .stream()
                .collect(Collectors.toMap(ProductDto::getId, product -> product));

        // persist order to database
        List<ProductDtoWrapper> productDtoWrappers = request.getOrderItems().stream().map(orderItem -> {
            ProductDto product = productsMap.get(orderItem.getProductId());
            return new ProductDtoWrapper(product, orderItem.getQuantity());
        }).toList();

        OrderEntity order = orderService.persistOrder(productDtoWrappers, request.getCustomerId());

        // get least busy courier
        CourierEntity courier = courierService.getLeastBusy();

        // create delivery
        deliveryService.createPending(order, courier);
    }

    @Override
    public List<OrderDto> getAll() {
        return orderService.getAll().stream().map(OrderDto::transform).collect(Collectors.toList());
    }
}
