package com.example.order.service.impl;

import com.example.order.config.RabbitMQConfig;
import com.example.order.model.NotificationDTO;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductQueueServiceImpl {
    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQConfig rabbitMQConfig;
    private final Gson gson;

    public void sendNotification(NotificationDTO notificationDTO) {
        String json = gson.toJson(notificationDTO);
        logOutgoingMessage(rabbitMQConfig.getExchangeName(), rabbitMQConfig.getPadRoutingKey(), json);
        rabbitTemplate.convertAndSend(rabbitMQConfig.getExchangeName(), rabbitMQConfig.getPadRoutingKey(), json);
    }

    private void logOutgoingMessage(String exchange, String routingKey, String message) {
        log.info("Sending message to exchange: {}, routing key: {}, message: {}", exchange, routingKey, message);
    }

}
