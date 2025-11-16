package com.example.health_platform.modules.medicalrecord.model;

import com.example.health_platform.auth.model.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "medical_profiles")
public class MedicalProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link to User
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String bloodGroup;

    private String allergies;

    private String chronicDiseases;

    private String medications;
}
