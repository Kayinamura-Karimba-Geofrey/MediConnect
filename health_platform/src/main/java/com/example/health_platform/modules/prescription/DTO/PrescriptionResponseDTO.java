package com.example.health_platform.modules.prescription.DTO;

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
