package com.example.health_platform.modules.notification.controller;



import com.example.health_platform.modules.notification.DTO.NotificationResponse;
import com.example.health_platform.modules.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    
    @GetMapping("/my")
    public ResponseEntity<List<NotificationResponse>> getMyNotifications(
            @RequestParam Long userId 
    ) {
        return ResponseEntity.ok(notificationService.getMyNotifications(userId));
    }

    
    @PatchMapping("/read/{id}")
    public ResponseEntity<NotificationResponse> markAsRead(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.markAsRead(id));
    }
}
