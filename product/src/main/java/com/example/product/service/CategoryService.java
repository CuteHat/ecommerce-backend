package com.example.product.service;

import com.example.product.persistence.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {
    List<CategoryEntity> getCategories();

    CategoryEntity getCategory(Long id);
}
