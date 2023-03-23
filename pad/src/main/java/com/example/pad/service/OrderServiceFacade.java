package com.example.pad.service;

import com.example.pad.model.OrderCreateRequest;
import com.example.pad.model.OrderDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface OrderServiceFacade {
    // copilot write javadoc for me
    @Transactional
    void createPending(OrderCreateRequest request);

    List<OrderDto> getAll();
}
