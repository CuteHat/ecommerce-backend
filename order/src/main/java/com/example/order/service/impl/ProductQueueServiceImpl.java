package com.example.order.service.impl;

import com.example.order.config.RabbitMQConfig;
import com.example.order.model.OrderItem;
import com.example.order.service.ProductQueueService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductQueueServiceImpl implements ProductQueueService {
    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQConfig rabbitMQConfig;
    private final Gson gson;

    @Override
    public void decrementStock(List<OrderItem> orderItems) {
        String json = gson.toJson(orderItems);
        logOutgoingMessage(rabbitMQConfig.getExchangeName(), rabbitMQConfig.getProductRoutingKey(), json);
        rabbitTemplate.convertAndSend(rabbitMQConfig.getExchangeName(), rabbitMQConfig.getProductRoutingKey(), json);
    }

    private void logOutgoingMessage(String exchange, String routingKey, String message) {
        log.info("Sending message to exchange: {}, routing key: {}, message: {}", exchange, routingKey, message);
    }

}
