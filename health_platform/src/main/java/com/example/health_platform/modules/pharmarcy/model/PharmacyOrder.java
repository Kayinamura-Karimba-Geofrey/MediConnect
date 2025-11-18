package com.example.health_platform.modules.pharmarcy.model;

import com.example.health_platform.auth.model.User;
import com.example.health_platform.modules.doctor.model.Prescription1;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class PharmacyOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private User patient;

    @ManyToOne
    @JoinColumn(name = "prescription_id")
    private Prescription1 prescription;

    @ElementCollection
    private List<String> medicines;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;

    private LocalDateTime createdAt = LocalDateTime.now();

    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getPatient() { return patient; }
    public void setPatient(User patient) { this.patient = patient; }

    public Prescription1 getPrescription() { return prescription; }
    public void setPrescription(Prescription1 prescription) { this.prescription = prescription; }

    public List<String> getMedicines() { return medicines; }
    public void setMedicines(List<String> medicines) { this.medicines = medicines; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
