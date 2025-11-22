package com.example.health_platform.modules.medicalrecord.model;
import com.example.health_platform.auth.model.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    private String bloodGroup;
    private String allergies;        
    private String chronicDiseases;  
    private String medications;      
    private String emergencyContactName;
    private String emergencyContactPhone;
}
