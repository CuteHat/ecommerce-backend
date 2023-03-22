package com.example.product.service.impl;

import com.example.product.model.ProductDto;
import com.example.product.service.ProductService;
import com.example.product.service.ProductServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceFacadeImpl implements ProductServiceFacade {
    private final ProductService productService;

    @Override
    public List<ProductDto> getProducts() {
        return productService.getProducts().stream().map(ProductDto::transform).collect(Collectors.toList());
    }

    @Override
    public ProductDto getProduct(Long id) {
        return ProductDto.transform(productService.getProduct(id));
    }

    @Override
    public ProductDto decrementStock(Long id, Integer quantity) {
        return ProductDto.transform(productService.decrementStock(id, quantity));
    }

    @Override
    public void decrementStockBatch(Map<Long, Integer> products) {
        productService.decrementStockBatch(products);
    }

    @Override
    public Boolean productStockIsAvailable(Long id, Integer quantity) {
        return productService.productStockIsAvailable(id, quantity);
    }

    @Override
    public Boolean productStockIsAvailableBatch(Map<Long, Integer> products) {
        return productService
                .productStockIsAvailableBatch(products)
                .entrySet()
                .stream()
                .allMatch(Map.Entry::getValue);
    }
}
