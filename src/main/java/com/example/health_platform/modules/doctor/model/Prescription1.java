package com.example.health_platform.modules.doctor.model;


import com.example.health_platform.auth.model.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Prescription1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private User doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private User patient;

    private String notes; 

    private LocalDateTime createdAt = LocalDateTime.now();

    
    @ElementCollection
    private List<String> medicines;

    
}
