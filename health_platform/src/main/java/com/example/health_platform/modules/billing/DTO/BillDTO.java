package com.example.health_platform.modules.billing.DTO;

import com.example.health_platform.modules.billing.model.BillStatus;

public class BillDTO {

    private Long id;
    private Long patientId;
    private Double amount;
    private String description;
    private BillStatus status;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BillStatus getStatus() { return status; }
    public void setStatus(BillStatus status) { this.status = status; }
}
