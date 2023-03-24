package com.example.order.controller;

import com.example.order.model.OrderCreateRequest;
import com.example.order.service.OrderServiceFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
@Validated
public class OrderController {
    private final OrderServiceFacade orderServiceFacade;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid OrderCreateRequest request) {
        orderServiceFacade.createOrder(request);
        return ResponseEntity.ok().build();
    }
}
