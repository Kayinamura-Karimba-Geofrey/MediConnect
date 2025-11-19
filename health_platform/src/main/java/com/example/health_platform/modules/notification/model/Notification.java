package com.example.health_platform.modules.notification.model;

import com.example.health_platform.auth.model.User;



import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;


    @Builder.Default
    private boolean isRead = false;
    
    @Builder.Default
    private LocalDateTime timeStamp = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
