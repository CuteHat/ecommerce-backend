package com.example.order.productapi;

import com.example.order.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DecrementStockDto {
    private Long id;
    private Integer quantity;

    public static DecrementStockDto transform(OrderItem orderItem) {
        return new DecrementStockDto(orderItem.getProductId(), orderItem.getQuantity());
    }
}