package com.example.product.service;

import com.example.product.model.ProductDto;

import java.util.List;
import java.util.Map;

public interface ProductServiceFacade {
    List<ProductDto> getProducts();

    ProductDto getProduct(Long id);

    ProductDto decrementStock(Long id, Integer quantity);

    void decrementStockBatch(Map<Long, Integer> products);

    Boolean productStockIsAvailable(Long id, Integer quantity);

    Boolean productStockIsAvailableBatch(Map<Long, Integer> products);
}
