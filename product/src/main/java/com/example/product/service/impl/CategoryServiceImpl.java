package com.example.product.service.impl;

import com.example.product.persistence.entity.CategoryEntity;
import com.example.product.persistence.repository.CategoryRepository;
import com.example.product.service.CategoryService;
import com.example.product.util.HandledExceptionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;

    public List<CategoryEntity> getCategories() {
        return repository.findAll();
    }

    private CategoryEntity getCategory(Long id) {
        return repository.findById(id).orElseThrow(() -> HandledExceptionFactory.getHandledException("Category not found"));
    }
}
