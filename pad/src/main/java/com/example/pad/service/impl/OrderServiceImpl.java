package com.example.pad.service.impl;

import com.example.order.model.OrderCreateRequest;
import com.example.order.model.OrderItem;
import com.example.order.persistence.entity.OrderEntity;
import com.example.order.persistence.entity.OrderItemEntity;
import com.example.order.persistence.model.OrderStatus;
import com.example.order.persistence.repository.OrderRepository;
import com.example.order.productapi.Product;
import com.example.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public OrderEntity persistOrder(OrderCreateRequest request, Long customerId) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        List<OrderItemEntity> orderItemEntities = new ArrayList<>();

        for (OrderItem item : request.getOrderItems()) {
            OrderItemEntity orderItemEntity = new OrderItemEntity();
            Product product = item.getProduct();
            orderItemEntity.setProductId(product.getId());
            orderItemEntity.setProductPrice(product.getPrice());
            orderItemEntity.setQuantity(item.getQuantity());
            orderItemEntity.setSellerId(product.getSellerId());
            orderItemEntities.add(orderItemEntity);
            totalPrice = totalPrice.add(product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCustomerId(customerId);
        orderEntity.setOrderItems(orderItemEntities);
        orderEntity.setTotalPrice(totalPrice);
        orderEntity.setStatus(OrderStatus.PENDING);

        return orderRepository.save(orderEntity);
    }

}
