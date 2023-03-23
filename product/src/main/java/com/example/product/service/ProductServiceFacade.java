package com.example.product.service;

import com.example.product.model.DecrementStockDto;
import com.example.product.model.ProductDto;

import java.util.List;

public interface ProductServiceFacade {
    List<ProductDto> getProducts();

    ProductDto getProduct(Long id);

    ProductDto decrementStock(Long id, Integer quantity);

    void decrementStockBatch(List<DecrementStockDto> products);

    Boolean productStockIsAvailable(Long id, Integer quantity);

    Boolean productStockIsAvailableBatch(List<DecrementStockDto> products);

    List<ProductDto> filter(List<Long> id);
}
