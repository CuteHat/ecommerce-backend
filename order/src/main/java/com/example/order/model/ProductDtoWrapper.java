package com.example.order.model;

import com.example.order.productapi.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDtoWrapper {
    private ProductDto product;
    private Integer quantity;
}
