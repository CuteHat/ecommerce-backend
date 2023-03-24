package com.example.notification.service.impl;

import com.example.notification.persistence.model.NotificationDto;
import com.example.notification.service.NotificationServiceFacade;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@RequiredArgsConstructor
public class NotificationQueueListener {
    private final Gson gson;
    private final NotificationServiceFacade notificationServiceFacade;

    @RabbitListener(queues = "notification.queue")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
        String messageBody = new String(message.getBytes(), StandardCharsets.UTF_8);
        Type notificationListType = new TypeToken<List<NotificationDto>>() {
        }.getType();
        List<NotificationDto> notifications = gson.fromJson(messageBody, notificationListType);
        notificationServiceFacade.sendNotifications(notifications);
    }
}
