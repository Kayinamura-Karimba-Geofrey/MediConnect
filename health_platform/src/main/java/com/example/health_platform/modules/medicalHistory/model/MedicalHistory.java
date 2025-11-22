package com.example.health_platform.modules.medicalHistory.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import lombok.Data;

import java.time.LocalDate;

@Entity
@Data   
@AllArgsConstructor
 
@Table(name = "medical_histories")
public class MedicalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String patientName;

    @Column(nullable = false)
    private LocalDate dateOfRecord;

    @Column(length = 1000)
    private String diagnosis;

    @Column(length = 2000)
    private String treatment;

    @Column(length = 2000)
    private String notes;

    
    public MedicalHistory() {
    }

    public MedicalHistory(String patientName, LocalDate dateOfRecord, String diagnosis, String treatment, String notes) {
        this.patientName = patientName;
        this.dateOfRecord = dateOfRecord;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.notes = notes;
    }

    
}
