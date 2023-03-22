package com.example.order.service.impl;

import com.example.order.model.OrderCreateRequest;
import com.example.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceFacadeImpl {
    private final OrderService orderService;

    public void createOrder(OrderCreateRequest request) {
        // TODO check if all of the products are available
        // TODO persist order to database
        // TODO send reduce quantities command to product service
        // TODO send PENDING status to Packaging and Delivery service
        // TODO sent notifications to customer and seller
    }
}
