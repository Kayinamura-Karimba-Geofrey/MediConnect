package com.example.health_platform.modules.medicalrecord.model;

import com.example.health_platform.auth.model.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "medical_records")
@Data
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") 
    private User user;

    private String diagnosis;
    private String scanUrl;
    private String labResultUrl;

    private LocalDateTime createdAt = LocalDateTime.now();
}
