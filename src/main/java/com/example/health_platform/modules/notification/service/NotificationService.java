package com.example.health_platform.modules.notification.service;


import com.example.health_platform.modules.notification.DTO.NotificationResponse;
import com.example.health_platform.modules.notification.model.Notification;
import com.example.health_platform.modules.notification.repository.NotificationRepository;
import com.example.health_platform.auth.model.User;
import com.example.health_platform.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    
    public List<NotificationResponse> getMyNotifications(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();

        return notificationRepository.findByUserOrderByTimeStampDesc(user)
                .stream()
                .map(n -> NotificationResponse.builder()
                        .id(n.getId())
                        .message(n.getMessage())
                        .isRead(n.isRead())
                        .timeStamp(n.getTimeStamp())
                        .build())
                .toList();
    }

    
    public NotificationResponse markAsRead(Long id) {
        Notification n = notificationRepository.findById(id).orElseThrow();
        n.setRead(true);
        notificationRepository.save(n);

        return NotificationResponse.builder()
                .id(n.getId())
                .message(n.getMessage())
                .isRead(n.isRead())
                .timeStamp(n.getTimeStamp())
                .build();
    }

    
    public void createNotification(Long userId, String message) {
        User user = userRepository.findById(userId).orElseThrow();

        Notification n = Notification.builder()
                .user(user)
                .message(message)
                .build();

        notificationRepository.save(n);
    }
}
