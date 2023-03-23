package com.example.order.productapi;

import java.util.List;

public interface ProductApiService {
    Boolean stockAvailable(List<DecrementStockDto> products);

    List<ProductDto> filter(List<Long> productIds);
}
