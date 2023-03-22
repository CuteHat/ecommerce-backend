package com.example.product.service;

import com.example.product.model.CategoryDto;

import java.util.List;

public interface CategoryServiceFacade {
    List<CategoryDto> getCategories();

    CategoryDto getCategory(Long id);
}
