package com.example.notification.service;

import com.example.notification.persistence.model.NotificationDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface NotificationServiceFacade {
    @Transactional
    void sendNotifications(List<NotificationDto> notificationDtoList);
}
