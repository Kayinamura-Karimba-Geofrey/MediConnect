package com.example.health_platform.modules.emergency.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class EmergencyLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;
    private String accessedBy;
    private String status;
    private LocalDateTime accessedAt = LocalDateTime.now();

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getAccessedBy() { return accessedBy; }
    public void setAccessedBy(String accessedBy) { this.accessedBy = accessedBy; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getAccessedAt() { return accessedAt; }
    public void setAccessedAt(LocalDateTime accessedAt) { this.accessedAt = accessedAt; }
}
