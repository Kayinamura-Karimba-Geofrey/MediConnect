package com.example.health_platform.modules.notification.DTO;



import lombok.*;

@Getter
@Setter
public class CreateNotificationRequest {
    private Long userId;
    private String message;
}
