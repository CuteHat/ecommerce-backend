package com.example.notification.service.impl;

import com.example.notification.persistence.entity.NotificationEntity;
import com.example.notification.persistence.model.NotificationType;
import com.example.notification.persistence.repository.NotificationRepository;
import com.example.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository repository;

    @Override
    public NotificationEntity create(String message, NotificationType type, String recipient, Long userId) {
        NotificationEntity entity = new NotificationEntity();
        entity.setMessage(message);
        entity.setType(type);
        entity.setRecipient(recipient);
        entity.setUserId(userId);
        entity.setRead(false);
        return repository.save(entity);
    }
}
