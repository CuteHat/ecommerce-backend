package com.example.pad.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderCreateRequest {
    private List<OrderItem> orderItems;
    private Long customerId;
}
