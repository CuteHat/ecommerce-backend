package com.example.pad.service;


import com.example.pad.model.OrderCreateRequest;
import com.example.pad.persistence.entity.OrderEntity;

public interface OrderService {
    OrderEntity persistOrder(OrderCreateRequest request, Long customerId);
}
