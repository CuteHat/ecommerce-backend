package com.example.notification.service;

import com.example.notification.persistence.model.NotificationDto;

import java.util.List;

public interface MailService {
    void sendBatch(List<NotificationDto> notificationDtoList);

    void sendMail(String recipient, String message);
}
