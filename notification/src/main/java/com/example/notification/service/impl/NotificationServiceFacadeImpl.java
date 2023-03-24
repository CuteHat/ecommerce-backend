package com.example.notification.service.impl;

import com.example.notification.persistence.model.NotificationDto;
import com.example.notification.persistence.model.NotificationType;
import com.example.notification.service.MailService;
import com.example.notification.service.NotificationService;
import com.example.notification.service.NotificationServiceFacade;
import com.example.notification.service.SMSService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceFacadeImpl implements NotificationServiceFacade {
    public final NotificationService notificationService;
    public final SMSService smsService;
    public final MailService mailService;

    @Override
    @Transactional
    public void sendNotifications(List<NotificationDto> notificationDtoList) {
        notificationService.createBatch(notificationDtoList);
        smsService.sendBatch(notificationDtoList.stream().filter(notificationDto -> notificationDto.getType() == NotificationType.SMS).toList());
        mailService.sendBatch(notificationDtoList.stream().filter(notificationDto -> notificationDto.getType() == NotificationType.EMAIL).toList());
    }
}
