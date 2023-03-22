package com.example.product.controller;

import com.example.product.model.ProductDto;
import com.example.product.service.ProductServiceFacade;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductServiceFacade productServiceFacade;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts() {
        return ResponseEntity.ok(productServiceFacade.getProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable @Min(1) Long id) {
        return ResponseEntity.ok(productServiceFacade.getProduct(id));
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<ProductDto> decrementStock(@PathVariable @Min(1) Long id, @RequestParam @Min(1) Integer quantity) {
        return ResponseEntity.ok(productServiceFacade.decrementStock(id, quantity));
    }

    @PutMapping("/stock")
    public ResponseEntity<Void> decrementStockBatch(@RequestBody Map<Long, Integer> products) {
        productServiceFacade.decrementStockBatch(products);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/stock")
    public ResponseEntity<Boolean> productStockIsAvailable(@PathVariable @Min(1) Long id, @RequestParam @Min(1) Integer quantity) {
        return ResponseEntity.ok(productServiceFacade.productStockIsAvailable(id, quantity));
    }

    @GetMapping("/stock")
    public ResponseEntity<Boolean> productStockIsAvailableBatch(@RequestBody Map<Long, Integer> products) {
        return ResponseEntity.ok(productServiceFacade.productStockIsAvailableBatch(products));
    }
}