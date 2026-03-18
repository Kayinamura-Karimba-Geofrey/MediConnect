package com.example.health_platform.modules.notification.dto;


import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponse {
    private Long id;
    private String message;
    private boolean isRead;
    private LocalDateTime timeStamp;
}

