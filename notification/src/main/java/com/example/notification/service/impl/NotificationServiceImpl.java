package com.example.notification.service.impl;

import com.example.notification.persistence.entity.NotificationEntity;
import com.example.notification.persistence.model.NotificationDto;
import com.example.notification.persistence.model.NotificationType;
import com.example.notification.persistence.repository.NotificationRepository;
import com.example.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository repository;

    @Override
    public List<NotificationEntity> createBatch(List<NotificationDto> notificationDtoList) {
        List<NotificationEntity> notificationEntities = new ArrayList<>();
        for (NotificationDto notificationDto : notificationDtoList) {
            NotificationEntity entity = new NotificationEntity();
            entity.setMessage(notificationDto.getMessage());
            entity.setType(notificationDto.getType());
            entity.setRecipient(notificationDto.getRecipient());
            notificationEntities.add(entity);
        }

        return repository.saveAll(notificationEntities);
    }
    @Override
    public NotificationEntity create(String message, NotificationType type, String recipient, Long userId) {
        NotificationEntity entity = new NotificationEntity();
        entity.setMessage(message);
        entity.setType(type);
        entity.setRecipient(recipient);
        return repository.save(entity);
    }
}
