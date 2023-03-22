package com.example.product.service;

import com.example.product.persistence.entity.ProductEntity;

import java.util.List;
import java.util.Map;

public interface ProductService {
    List<ProductEntity> getProducts();

    ProductEntity getProduct(Long id);

    Map<Long, ProductEntity> decrementStockBatch(Map<Long, Integer> products);

    ProductEntity decrementStock(Long id, Integer quantity);

    Map<Long, Boolean> productStockIsAvailableBatch(Map<Long, Integer> products);

    Boolean productStockIsAvailable(Long id, Integer quantity);
}
