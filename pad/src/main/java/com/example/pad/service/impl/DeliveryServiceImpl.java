package com.example.pad.service.impl;

import com.example.pad.persistence.entity.CourierEntity;
import com.example.pad.persistence.entity.DeliveryEntity;
import com.example.pad.persistence.entity.OrderEntity;
import com.example.pad.persistence.model.DeliveryStatus;
import com.example.pad.persistence.repository.DeliveryRepository;
import com.example.pad.persistence.specification.DeliverySpecifications;
import com.example.pad.service.DeliveryService;
import com.example.pad.util.HandledExceptionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {
    private final DeliveryRepository deliveryRepository;

    @Override
    public DeliveryEntity createPending(OrderEntity order, CourierEntity courier) {
        DeliveryEntity deliveryEntity = new DeliveryEntity();
        deliveryEntity.setOrder(order);
        deliveryEntity.setCourier(courier);
        deliveryEntity.setStatus(DeliveryStatus.PENDING);
        return deliveryRepository.save(deliveryEntity);
    }

    @Override
    public DeliveryEntity get(Long id) {
        return deliveryRepository
                .findById(id)
                .orElseThrow(() -> HandledExceptionFactory.getHandledException("Delivery does not exist"));
    }

    @Override
    public DeliveryEntity updateStatus(Long deliverId, DeliveryStatus status) {
        DeliveryEntity deliveryEntity = get(deliverId);
        deliveryEntity.setStatus(status);
        return deliveryRepository.save(deliveryEntity);
    }

    @Override
    public List<DeliveryEntity> filter(DeliveryStatus status,
                                       BigDecimal totalPriceFrom, BigDecimal totalPriceTo,
                                       Timestamp timeFrom, Timestamp timeTo) {
        Specification<DeliveryEntity> specification = Specification
                .where(DeliverySpecifications.hasStatus(status))
                .and(DeliverySpecifications.totalPriceBetween(totalPriceFrom, totalPriceTo))
                .and(DeliverySpecifications.deliveredAfter(timeFrom))
                .and(DeliverySpecifications.deliveredBefore(timeTo));

        return deliveryRepository.findAll(specification);
    }
}
