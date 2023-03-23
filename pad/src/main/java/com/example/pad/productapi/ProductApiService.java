package com.example.pad.productapi;

import java.util.List;

public interface ProductApiService {
    List<ProductDto> filter(List<Long> productIds);
}
