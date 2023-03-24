package com.example.notification.service;

import com.example.notification.persistence.model.NotificationDto;

import java.util.List;

public interface SMSService {
    void sendBatch(List<NotificationDto> notificationDtoList);

    void send(String recipient, String message);
}
