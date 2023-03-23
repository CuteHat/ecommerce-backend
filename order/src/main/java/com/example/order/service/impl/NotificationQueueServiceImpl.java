package com.example.order.service.impl;

import com.example.order.config.RabbitMQConfig;
import com.example.order.model.NotificationDto;
import com.example.order.service.NotificationQueueService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationQueueServiceImpl implements NotificationQueueService {
    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQConfig rabbitMQConfig;
    private final Gson gson;

    @Override
    public void sendMessage(List<NotificationDto> notificationDtos) {
        String json = gson.toJson(notificationDtos);
        logOutgoingMessage(rabbitMQConfig.getExchangeName(), rabbitMQConfig.getNotificationRoutingKey(), json);
        rabbitTemplate.convertAndSend(rabbitMQConfig.getExchangeName(), rabbitMQConfig.getNotificationRoutingKey(), json);
    }

    private void logOutgoingMessage(String exchange, String routingKey, String message) {
        log.info("Sending message to exchange: {}, routing key: {}, message: {}", exchange, routingKey, message);
    }
}
