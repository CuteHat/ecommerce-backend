package com.example.pad.service.impl;

import com.example.pad.model.OrderCreateRequest;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
@Slf4j
public class PADQueueListener {
    private final Gson gson;
    private final OrderServiceFacadeImpl orderServiceFacade;

    @RabbitListener(queues = "${rabbitmq.pad.queueName}")
    public void receiveMessage(String message) {
        log.info("Received message: " + message);
        String messageBody = new String(message.getBytes(), StandardCharsets.UTF_8);
        OrderCreateRequest request = gson.fromJson(messageBody, OrderCreateRequest.class);
        orderServiceFacade.createPending(request);
    }
}
