package com.example.order.service;

import com.example.order.model.OrderCreateRequest;
import jakarta.transaction.Transactional;

public interface OrderServiceFacade {
    @Transactional
    void createOrder(OrderCreateRequest request);
}
