package com.example.health_platform.modules.doctor.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime visitDate;

    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getVisitDate() { return visitDate; }
    public void setVisitDate(LocalDateTime visitDate) { this.visitDate = visitDate; }
}

