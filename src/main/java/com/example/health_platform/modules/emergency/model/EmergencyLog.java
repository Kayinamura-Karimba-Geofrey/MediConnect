package com.example.health_platform.modules.emergency.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class EmergencyLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;
    private String accessedBy;
    private String status;
    private LocalDateTime accessedAt = LocalDateTime.now();

    
}
