package com.example.health_platform.modules.medicalrecord.model;

import jakarta.persistence.*;
import lombok.*;
import com.example.health_platform.auth.model.User;





@Entity
@Table(name = "medical_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bloodGroup;           // A+, O-, etc.
    private String allergies;            // comma-separated or JSON
    private String chronicDiseases;      // diabetes, hypertension
    private String medications;          // active medications

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;
}
