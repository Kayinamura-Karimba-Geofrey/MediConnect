package com.example.health_platform.modules.emergency.model;



import com.example.health_platform.auth.model.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class EmergencyToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User patient;

    private String token;

    private LocalDateTime expiresAt;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getPatient() { return patient; }
    public void setPatient(User patient) { this.patient = patient; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public LocalDateTime getExpiresAt() { return expiresAt; }
    public void setExpiresAt(LocalDateTime expiresAt) { this.expiresAt = expiresAt; }
}

