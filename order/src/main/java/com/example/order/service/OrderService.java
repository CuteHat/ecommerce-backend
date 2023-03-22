package com.example.order.service;

import com.example.order.model.OrderCreateRequest;
import com.example.order.persistence.entity.OrderEntity;

public interface OrderService {
    OrderEntity persistOrder(OrderCreateRequest request, Long customerId);
}
