package com.example.pad.service;

import com.example.pad.model.DeliveryDto;
import com.example.pad.persistence.model.DeliveryStatus;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public interface DeliveryServiceFacade {
    List<DeliveryDto> filter(DeliveryStatus status,
                             BigDecimal totalPriceFrom, BigDecimal totalPriceTo,
                             Timestamp timeFrom, Timestamp timeTo);

    DeliveryDto updateStatus(Long deliveryId, DeliveryStatus status);
}
