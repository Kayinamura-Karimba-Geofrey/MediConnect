package com.example.health_platform.modules.medicalrecord.model;

import com.example.health_platform.auth.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Patient
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private User patient;

    // Doctor
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private User doctor;

    private String diagnosis;
    private String scanUrl;       // e.g. X-ray, MRI image link
    private String labResult;     // Lab report file link
    private String notes;

    private LocalDateTime createdAt;
}
