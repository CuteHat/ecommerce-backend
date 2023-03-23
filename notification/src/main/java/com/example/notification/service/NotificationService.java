package com.example.notification.service;

import com.example.notification.persistence.entity.NotificationEntity;
import com.example.notification.persistence.model.NotificationType;

public interface NotificationService {
    NotificationEntity create(String message, NotificationType type, String destination, Long userId);
}
