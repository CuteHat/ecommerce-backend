package com.example.notification.service.impl;

import com.example.notification.persistence.model.NotificationDto;
import com.example.notification.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MailServiceImpl implements MailService {

    @Override
    public void sendBatch(List<NotificationDto> notificationDtoList) {
        notificationDtoList.forEach(notificationDto -> {
            sendMail(notificationDto.getRecipient(), notificationDto.getMessage());
        });
    }

    @Override
    public void sendMail(String recipient, String message) {
        log.info("Sending mail to " + recipient + " with message: " + message);
    }
}
