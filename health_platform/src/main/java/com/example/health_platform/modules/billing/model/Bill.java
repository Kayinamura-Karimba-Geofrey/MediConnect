package com.example.health_platform.modules.billing.model;

import com.example.health_platform.auth.model.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private User patient;

    private Double amount;

    private String description;

    @Enumerated(EnumType.STRING)
    private BillStatus status = BillStatus.PENDING;

    private LocalDateTime createdAt = LocalDateTime.now();

    
}
