package com.example.product.service.impl;

import com.example.product.model.DecrementStockDto;
import com.example.product.service.ProductServiceFacade;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationQueueListener {
    private final Gson gson;
    private final ProductServiceFacade productServiceFacade;

    @RabbitListener(queues = "${rabbitmq.product.queueName}")
    public void receiveMessage(String message) {
        log.info("Received message: " + message);
        String messageBody = new String(message.getBytes(), StandardCharsets.UTF_8);
        Type notificationListType = new TypeToken<List<DecrementStockDto>>() {
        }.getType();
        List<DecrementStockDto> decrementStockDtos = gson.fromJson(messageBody, notificationListType);
        productServiceFacade.decrementStockBatch(decrementStockDtos);
    }
}
