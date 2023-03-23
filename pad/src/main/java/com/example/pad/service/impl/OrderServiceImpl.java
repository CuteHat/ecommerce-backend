package com.example.pad.service.impl;

import com.example.pad.model.ProductDtoWrapper;
import com.example.pad.persistence.entity.OrderEntity;
import com.example.pad.persistence.entity.OrderItemEntity;
import com.example.pad.persistence.repository.OrderRepository;
import com.example.pad.productapi.ProductDto;
import com.example.pad.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public OrderEntity persistOrder(List<ProductDtoWrapper> productDtoWrappers, Long customerId) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        List<OrderItemEntity> orderItemEntities = new ArrayList<>();

        for (ProductDtoWrapper item : productDtoWrappers) {
            OrderItemEntity orderItemEntity = new OrderItemEntity();
            ProductDto product = item.getProduct();
            orderItemEntity.setProductId(product.getId());
            orderItemEntity.setProductName(product.getName());
            orderItemEntity.setProductPrice(product.getPrice());
            orderItemEntity.setQuantity(item.getQuantity());
            orderItemEntity.setSellerId(product.getSellerId());
            orderItemEntities.add(orderItemEntity);
            totalPrice = totalPrice.add(product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCustomerId(customerId);
        orderEntity.setOrderItems(orderItemEntities);
        orderEntity.setTotalPrice(totalPrice);

        return orderRepository.save(orderEntity);
    }

    @Override
    public List<OrderEntity> getAll() {
        return orderRepository.findAll();
    }
}
