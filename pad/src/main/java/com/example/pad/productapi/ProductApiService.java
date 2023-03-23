package com.example.pad.productapi;

import java.util.List;

public interface ProductService {
    List<ProductDto> filter(List<Long> productIds);
}
