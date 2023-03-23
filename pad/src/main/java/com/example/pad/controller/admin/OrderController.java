package com.example.pad.controller.admin;

import com.example.pad.model.OrderDto;
import com.example.pad.service.OrderServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/order")
@RequiredArgsConstructor
@Validated
public class OrderController {
    private final OrderServiceFacade orderServiceFacade;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAll() {
        return ResponseEntity.ok(orderServiceFacade.getAll());
    }
}
