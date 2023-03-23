package com.example.order.service;

import com.example.order.model.NotificationDto;
import com.example.order.model.OrderItem;
import com.example.order.productapi.ProductDto;

import java.math.BigDecimal;
import java.util.List;

public interface NotificationService {
    List<NotificationDto> createSellerNotifications(List<OrderItem> orderItems, List<ProductDto> productDtos);

    NotificationDto createClientNotifications(Long orderId, BigDecimal totalPrice, String clientPhoneNumber);
}
