package com.example.pad.service.impl;

import com.example.pad.model.OrderCreateRequest;
import com.example.pad.model.OrderItemDto;
import com.example.pad.persistence.entity.OrderEntity;
import com.example.pad.persistence.entity.OrderItemEntity;
import com.example.pad.persistence.repository.OrderRepository;
import com.example.pad.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public OrderEntity persistOrder(OrderCreateRequest request) {
        List<OrderItemEntity> orderItemEntities = new ArrayList<>();

        for (OrderItemDto item : request.getOrderItems()) {
            OrderItemEntity orderItemEntity = new OrderItemEntity();
            orderItemEntity.setSystemOrderItemId(item.getId());
            orderItemEntity.setProductId(item.getProductId());
            orderItemEntity.setProductName(item.getProductName());
            orderItemEntity.setProductPrice(item.getProductPrice());
            orderItemEntity.setQuantity(item.getQuantity());
            orderItemEntity.setSellerId(item.getSellerId());
            orderItemEntities.add(orderItemEntity);
        }

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setSystemOrderId(request.getOrderId());
        orderEntity.setCustomerId(request.getCustomerId());
        orderEntity.setTotalPrice(request.getTotalPrice());
        orderEntity.setOrderItems(orderItemEntities);

        return orderRepository.save(orderEntity);
    }

    @Override
    public List<OrderEntity> getAll() {
        return orderRepository.findAll();
    }
}
