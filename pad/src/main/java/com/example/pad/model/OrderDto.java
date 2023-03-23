package com.example.pad.model;

import com.example.pad.persistence.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto {
    private Long id;
    private List<OrderItemDto> orderItems;
    private BigDecimal totalPrice;
    private Long customerId;

    public static OrderDto transform(OrderEntity orderEntity) {
        return new OrderDto(
                orderEntity.getId(),
                orderEntity.getOrderItems().stream().map(OrderItemDto::transform).toList(),
                orderEntity.getTotalPrice(),
                orderEntity.getCustomerId()
        );
    }
}
