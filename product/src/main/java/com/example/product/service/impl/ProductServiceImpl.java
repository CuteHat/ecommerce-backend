package com.example.product.service.impl;

import com.example.product.persistence.entity.ProductEntity;
import com.example.product.persistence.repository.ProductRepository;
import com.example.product.service.ProductService;
import com.example.product.util.HandledExceptionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;

    public List<ProductEntity> getProducts() {
        return repository.findAll();
    }

    public ProductEntity getProduct(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> HandledExceptionFactory.getHandledException("Product not found"));
    }

    /**
     * Decrements the stock of multiple products in one batch operation.
     *
     * @param products a map of product IDs and quantities to decrement.
     * @return a map of product IDs and updated ProductEntity objects.
     */
    public Map<Long, ProductEntity> decrementStockBatch(Map<Long, Integer> products) {
        Map<Long, ProductEntity> updatedProducts = new HashMap<>();
        for (Map.Entry<Long, Integer> entry : products.entrySet()) {
            Long productId = entry.getKey();
            Integer quantity = entry.getValue();
            ProductEntity product = decrementStock(productId, quantity);
            updatedProducts.put(productId, product);
        }
        return updatedProducts;
    }

    public ProductEntity decrementStock(Long id, Integer quantity) {
        ProductEntity product = getProduct(id);
        product.setStock(product.getStock() - quantity);
        return repository.save(product);
    }

    /**
     * Returns a map of product IDs and availability status for the given products and quantities.
     *
     * @param products a map of product IDs and quantities to check.
     * @return a map of product IDs and availability status.
     */
    public Map<Long, Boolean> productStockIsAvailableBatch(Map<Long, Integer> products) {
        Map<Long, Boolean> stockAvailabilityMap = new HashMap<>();
        for (Map.Entry<Long, Integer> entry : products.entrySet()) {
            Long productId = entry.getKey();
            Integer quantity = entry.getValue();
            stockAvailabilityMap.put(productId, productStockIsAvailable(productId, quantity));
        }
        return stockAvailabilityMap;
    }

    public Boolean productStockIsAvailable(Long id, Integer quantity) {
        ProductEntity product = getProduct(id);
        return product.getStock() >= quantity;
    }

}
