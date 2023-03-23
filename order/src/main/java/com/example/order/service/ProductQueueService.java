package com.example.order.service;

import com.example.order.model.OrderItem;

import java.util.List;

public interface ProductQueueService {

    void decrementStock(List<OrderItem> orderItems);
}
