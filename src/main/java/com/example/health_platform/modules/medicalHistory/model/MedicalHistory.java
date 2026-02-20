package com.example.health_platform.modules.medicalHistory.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data   
@AllArgsConstructor
@NoArgsConstructor
 
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

    
    

    
}
