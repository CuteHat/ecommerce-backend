package com.example.order.service;

import com.example.order.model.ProductDtoWrapper;
import com.example.order.persistence.entity.OrderEntity;

import java.util.List;

public interface OrderService {
    OrderEntity persistOrder(List<ProductDtoWrapper> productDtoWrappers, Long customerId);
}
