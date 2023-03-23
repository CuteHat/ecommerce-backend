package com.example.pad.service.impl;

import com.example.pad.model.DeliveryDto;
import com.example.pad.persistence.model.DeliveryStatus;
import com.example.pad.service.DeliveryService;
import com.example.pad.service.DeliveryServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryServiceFacadeImpl implements DeliveryServiceFacade {
    private final DeliveryService service;

    @Override
    public List<DeliveryDto> filter(DeliveryStatus status,
                                    BigDecimal totalPriceFrom, BigDecimal totalPriceTo,
                                    Timestamp timeFrom, Timestamp timeTo) {
        return service.filter(status, totalPriceFrom, totalPriceTo, timeFrom, timeTo)
                .stream()
                .map(DeliveryDto::transform)
                .toList();
    }

    @Override
    public DeliveryDto updateStatus(Long deliveryId, DeliveryStatus status) {
        return DeliveryDto.transform(service.updateStatus(deliveryId, status));
    }
}
