package com.example.health_platform.auth.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    private String username;

    @Column(nullable = false)
    private String password;

    private String phone;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private String verificationCode;
    private boolean verified = false;

    private String refreshToken;

    @Column(name = "profile_photo")
    @Lob
    private byte[] profilePhoto;

    

    private String bloodGroup;            
    private String allergies;             
    private String chronicDiseases;       
    private String emergencyContact;      

    private boolean suspended = false;     
    private boolean doctorApproved = false; 

}
