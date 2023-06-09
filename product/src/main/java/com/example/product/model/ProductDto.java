package com.example.product.model;

import com.example.product.persistence.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private Long sellerId;
    private String sellerEmail;
    private CategoryDto category;

    public static ProductDto transform(ProductEntity product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock(),
                product.getSellerId(),
                product.getSellerEmail(),
                CategoryDto.transform(product.getCategory())
        );
    }
}
