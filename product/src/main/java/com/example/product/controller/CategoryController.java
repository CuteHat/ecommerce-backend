package com.example.product.controller;

import com.example.product.model.CategoryDto;
import com.example.product.service.CategoryServiceFacade;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
@Validated
public class CategoryController {
    private final CategoryServiceFacade categoryServiceFacade;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategories() {
        return ResponseEntity.ok(categoryServiceFacade.getCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable @Min(1) Long id) {
        return ResponseEntity.ok(categoryServiceFacade.getCategory(id));
    }
}
