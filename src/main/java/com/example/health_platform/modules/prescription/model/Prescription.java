package com.example.health_platform.modules.prescription.model;

import com.example.health_platform.auth.model.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private User doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private User patient;

    @ElementCollection
    private List<String> medicines;

    private String notes;

    private boolean dispensed = false;

    
}
