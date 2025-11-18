package com.example.health_platform.modules.prescription.model;



import com.example.health_platform.auth.model.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "prescriptions")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private User doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private User patient;

    @Column(nullable = false)
    private String medicines; // comma-separated e.g., "Paracetamol 500mg, Amoxicillin 250mg"

    private String notes;

    @Column(nullable = false)
    private boolean dispensed = false;

    private LocalDateTime createdAt = LocalDateTime.now();
}
