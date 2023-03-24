package com.example.product.controller;

import com.example.product.model.DecrementStockDto;
import com.example.product.model.ProductDto;
import com.example.product.service.ProductServiceFacade;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // TODO to admin controller
//    @PutMapping("/{id}/stock")
//    public ResponseEntity<ProductDto> decrementStock(@PathVariable @Min(1) Long id, @RequestParam @Min(1) Integer quantity) {
//        return ResponseEntity.ok(productServiceFacade.decrementStock(id, quantity));
//    }

//    @PutMapping("/stock/batch")
//    public ResponseEntity<Void> decrementStockBatch(@RequestBody List<DecrementStockDto> products) {
//        productServiceFacade.decrementStockBatch(products);
//        return ResponseEntity.ok().build();
//    }

    @GetMapping("/{id}/stock/available")
    public ResponseEntity<Boolean> productStockIsAvailable(@PathVariable @Min(1) Long id, @RequestParam @Min(1) Integer quantity) {
        return ResponseEntity.ok(productServiceFacade.productStockIsAvailable(id, quantity));
    }

    @PostMapping("/stock/available/batch")
    public ResponseEntity<Boolean> productStockIsAvailableBatch(@RequestBody List<DecrementStockDto> products) {
        return ResponseEntity.ok(productServiceFacade.productStockIsAvailableBatch(products));
    }

    @GetMapping("filter")
    public ResponseEntity<List<ProductDto>> getAllByIds(@RequestParam List<Long> ids) {
        return ResponseEntity.ok(productServiceFacade.filter(ids));
    }
}
