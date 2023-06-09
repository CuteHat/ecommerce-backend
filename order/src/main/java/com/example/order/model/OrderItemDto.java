package com.example.order.model;

import com.example.order.persistence.entity.OrderItemEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemDto {
    private Long id;
    private Long productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer quantity;
    private Long sellerId;

    public static OrderItemDto transform(OrderItemEntity entity) {
        return new OrderItemDto(
                entity.getId(),
                entity.getProductId(),
                entity.getProductName(),
                entity.getProductPrice(),
                entity.getQuantity(),
                entity.getSellerId()
        );
    }
}
