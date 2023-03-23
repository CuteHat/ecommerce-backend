package com.example.product.service.impl;

import com.example.product.model.DecrementStockDto;
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
    public void decrementStockBatch(List<DecrementStockDto> products) {
        Map<Long, Integer> decrementStockMap = products.stream().collect(Collectors.toMap(DecrementStockDto::getId, DecrementStockDto::getQuantity));
        productService.decrementStockBatch(decrementStockMap);
    }

    @Override
    public Boolean productStockIsAvailable(Long id, Integer quantity) {
        return productService.productStockIsAvailable(id, quantity);
    }

    @Override
    public Boolean productStockIsAvailableBatch(List<DecrementStockDto> products) {
        Map<Long, Integer> productsAvailableMap = products
                .stream()
                .collect(Collectors.toMap(DecrementStockDto::getId, DecrementStockDto::getQuantity));
        return productService
                .productStockIsAvailableBatch(productsAvailableMap)
                .entrySet()
                .stream()
                .allMatch(Map.Entry::getValue);
    }
}
