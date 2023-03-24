package com.example.order.service;

import com.example.order.productapi.DecrementStockDto;

import java.util.List;

public interface ProductQueueService {

    void decrementStock(List<DecrementStockDto> orderItems);
}
