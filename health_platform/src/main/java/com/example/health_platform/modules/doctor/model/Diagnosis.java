package com.example.health_platform.modules.doctor.model;

import com.example.health_platform.auth.model.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Diagnosis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private User doctor;

    @ManyToOne
    @JoinColumn(name = "visit_id")
    private Visit1 visit;

    private String diagnosisText;
    private String treatment;

    private LocalDateTime createdAt = LocalDateTime.now();

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getDoctor() { return doctor; }
    public void setDoctor(User doctor) { this.doctor = doctor; }

    public Visit1 getVisit() { return visit; }
    public void setVisit(Visit1 visit) { this.visit = visit; }

    public String getDiagnosisText() { return diagnosisText; }
    public void setDiagnosisText(String diagnosisText) { this.diagnosisText = diagnosisText; }

    public String getTreatment() { return treatment; }
    public void setTreatment(String treatment) { this.treatment = treatment; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
