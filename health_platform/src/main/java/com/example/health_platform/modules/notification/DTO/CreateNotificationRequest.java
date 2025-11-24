package com.example.health_platform.modules.notification.DTO;



import lombok.*;

@Data
public class CreateNotificationRequest {
    private Long userId;
    private String message;
}
