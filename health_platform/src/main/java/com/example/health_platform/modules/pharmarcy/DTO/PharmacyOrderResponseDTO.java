package com.example.health_platform.modules.pharmarcy.DTO;


import com.example.health_platform.modules.pharmarcy.model.OrderStatus;

public class PharmacyOrderResponseDTO {

    private Long id;
    private Long prescriptionId;
    private String patientName;
    private OrderStatus status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPrescriptionId() { return prescriptionId; }
    public void setPrescriptionId(Long prescriptionId) { this.prescriptionId = prescriptionId; }

    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
}
