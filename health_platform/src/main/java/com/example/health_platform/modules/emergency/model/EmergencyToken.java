package com.example.health_platform.modules.emergency.model;



import com.example.health_platform.auth.model.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class EmergencyToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User patient;

    private String token;

    private LocalDateTime expiresAt;

    
}

