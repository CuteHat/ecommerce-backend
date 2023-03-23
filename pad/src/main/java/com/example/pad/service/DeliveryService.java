package com.example.pad.service;

import com.example.pad.persistence.entity.CourierEntity;
import com.example.pad.persistence.entity.DeliveryEntity;
import com.example.pad.persistence.entity.OrderEntity;
import com.example.pad.persistence.model.DeliveryStatus;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public interface DeliveryService {
    DeliveryEntity createPending(OrderEntity order, CourierEntity courier);

    DeliveryEntity get(Long id);


    DeliveryEntity updateStatus(Long deliverId, DeliveryStatus status);

    List<DeliveryEntity> filter(DeliveryStatus status,
                                BigDecimal totalPriceFrom, BigDecimal totalPriceTo,
                                Timestamp timeFrom, Timestamp timeTo);
}
