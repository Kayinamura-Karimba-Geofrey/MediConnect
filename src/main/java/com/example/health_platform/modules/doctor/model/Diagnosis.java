package com.example.health_platform.modules.doctor.model;

import com.example.health_platform.auth.model.User;
import com.example.health_platform.modules.visit.model.Visit;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Data
public class Diagnosis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private User doctor;
    @ManyToOne
    @JoinColumn(name = "visit_id")
    private Visit visit;
    private String diagnosisText;
    private String treatment;
    private String notes;

    private LocalDateTime createdAt = LocalDateTime.now();

    
}