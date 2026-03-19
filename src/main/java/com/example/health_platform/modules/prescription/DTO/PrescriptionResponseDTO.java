package com.example.health_platform.modules.prescription.dto;

import java.util.List;

import lombok.Data;
@Data
public class PrescriptionResponseDTO {

    private Long id;
    private Long doctorId;
    private Long patientId;
    private List<String> medicines;
    private String notes;
    private boolean dispensed;
    
}
