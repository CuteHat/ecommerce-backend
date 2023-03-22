package com.example.product.service.impl;

import com.example.product.model.CategoryDto;
import com.example.product.service.CategoryService;
import com.example.product.service.CategoryServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceFacadeImpl implements CategoryServiceFacade {
    private final CategoryService categoryService;

    @Override
    public List<CategoryDto> getCategories() {
        return categoryService.getCategories().stream().map(CategoryDto::transform).collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategory(Long id) {
        return CategoryDto.transform(categoryService.getCategory(id));
    }
}
