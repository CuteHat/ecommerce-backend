package com.example.notification.service.impl;

import com.example.notification.persistence.model.NotificationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SMSService implements com.example.notification.service.SMSService {

    @Override
    public void sendBatch(List<NotificationDto> notificationDtoList) {
        notificationDtoList.forEach(notificationDto -> {
            send(notificationDto.getRecipient(), notificationDto.getMessage());
        });
    }

    @Override
    public void send(String recipient, String message) {
        log.info("Sending SMS to " + recipient + " with message: " + message);
    }
}
