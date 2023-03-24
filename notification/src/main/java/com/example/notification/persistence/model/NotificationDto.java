package com.example.notification.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotificationDto {
    private String message;
    private NotificationType type;
    private String recipient;
}