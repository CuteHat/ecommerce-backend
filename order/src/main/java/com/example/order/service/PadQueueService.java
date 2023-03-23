package com.example.order.service;

import com.example.order.model.OrderCreateRequest;
import com.example.order.model.PadOrderCreateRequest;

public interface PadQueueService {
    void createPendingDelivery(PadOrderCreateRequest OrderCreateRequest);
}
