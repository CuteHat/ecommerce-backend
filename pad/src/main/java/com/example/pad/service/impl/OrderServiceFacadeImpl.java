package com.example.pad.service.impl;

import com.example.pad.model.OrderCreateRequest;
import com.example.pad.model.OrderDto;
import com.example.pad.persistence.entity.CourierEntity;
import com.example.pad.persistence.entity.OrderEntity;
import com.example.pad.service.CourierService;
import com.example.pad.service.DeliveryService;
import com.example.pad.service.OrderService;
import com.example.pad.service.OrderServiceFacade;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceFacadeImpl implements OrderServiceFacade {
    private final OrderService orderService;
    private final DeliveryService deliveryService;
    private final CourierService courierService;

    @Override
    @Transactional
    public void createPending(OrderCreateRequest request) {
        OrderEntity order = orderService.persistOrder(request);

        // get least busy courier
        CourierEntity courier = courierService.getLeastBusy();

        // create delivery
        deliveryService.createPending(order, courier);
    }

    @Override
    public List<OrderDto> getAll() {
        return orderService.getAll().stream().map(OrderDto::transform).collect(Collectors.toList());
    }
}
