package com.example.notification.service;

import com.example.notification.persistence.entity.NotificationEntity;
import com.example.notification.persistence.model.NotificationDto;
import com.example.notification.persistence.model.NotificationType;

import java.util.List;

public interface NotificationService {
    List<NotificationEntity> createBatch(List<NotificationDto> notificationDtoList);

    NotificationEntity create(String message, NotificationType type, String destination, Long userId);
}
