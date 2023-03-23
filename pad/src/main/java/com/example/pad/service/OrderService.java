package com.example.pad.service;


import com.example.pad.model.OrderCreateRequest;
import com.example.pad.model.ProductDtoWrapper;
import com.example.pad.persistence.entity.OrderEntity;

import java.util.List;

public interface OrderService {
    OrderEntity persistOrder(OrderCreateRequest request);

    List<OrderEntity> getAll();
}
