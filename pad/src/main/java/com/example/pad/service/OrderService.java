package com.example.pad.service;


import com.example.pad.model.ProductDtoWrapper;
import com.example.pad.persistence.entity.OrderEntity;

import java.util.List;

public interface OrderService {
    OrderEntity persistOrder(List<ProductDtoWrapper> productDtoWrappers, Long customerId);

    List<OrderEntity> getAll();
}
