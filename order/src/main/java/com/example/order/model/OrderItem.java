package com.example.order.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItem {
    @NotNull
    @Min(1)
    private Long productId;
    @NotNull
    @Min(1)
    private int quantity;
}
