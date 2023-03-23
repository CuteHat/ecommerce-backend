package com.example.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PadOrderCreateRequest {
    private Long orderId;
    private BigDecimal totalPrice;
    private Long customerId;
    private List<OrderItemDto> orderItems;
}
