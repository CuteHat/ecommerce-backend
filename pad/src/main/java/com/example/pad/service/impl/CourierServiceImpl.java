package com.example.pad.service.impl;

import com.example.pad.persistence.entity.CourierEntity;
import com.example.pad.persistence.repository.CourierRepository;
import com.example.pad.service.CourierService;
import com.example.pad.util.HandledExceptionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class CourierServiceImpl implements CourierService {
    private final CourierRepository courierRepository;

    @Override
    public CourierEntity getLeastBusy() {
        return courierRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(courier -> courier.getDeliveries().size()))
                .findFirst()
                .orElseThrow(() -> HandledExceptionFactory.getHandledException("Courier not found"));
    }
}
