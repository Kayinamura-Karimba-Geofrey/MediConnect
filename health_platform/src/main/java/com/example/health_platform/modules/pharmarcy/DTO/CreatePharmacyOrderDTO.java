package com.example.health_platform.modules.pharmarcy.DTO;



import jakarta.validation.constraints.NotNull;

public class CreatePharmacyOrderDTO {

    @NotNull
    private Long prescriptionId;

    public Long getPrescriptionId() { return prescriptionId; }
    public void setPrescriptionId(Long prescriptionId) { this.prescriptionId = prescriptionId; }
}

