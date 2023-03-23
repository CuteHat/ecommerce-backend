package com.example.order.service;

import com.example.order.model.NotificationDto;

import java.util.List;

public interface NotificationQueueService {
    void sendMessage(List<NotificationDto> notificationDtos);
}
