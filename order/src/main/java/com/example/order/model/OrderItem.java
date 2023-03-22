package com.example.order.model;

import com.example.order.productapi.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItem {
    private Product product;
    private int quantity;
}
