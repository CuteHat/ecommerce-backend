package com.example.pad.productapi;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductApiServiceImpl implements ProductApiService {
    @Value("${api.product.url}")
    private String baseUrl;

    @Override
    public List<ProductDto> filter(List<Long> productIds) {
        RestTemplate restTemplate = new RestTemplate();

        String productsFilterEndpoint = "/product/filter";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(baseUrl.concat(productsFilterEndpoint))
                .queryParam("ids", productIds);

        return restTemplate.exchange(
                uriBuilder.build().toUri(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProductDto>>() {
                }).getBody();
    }
}
