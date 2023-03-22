package com.example.product.model;

import com.example.product.persistence.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDto {
    private Long id;
    private String name;

    public static CategoryDto transform(CategoryEntity category) {
        return new CategoryDto(category.getId(), category.getName());
    }
}
