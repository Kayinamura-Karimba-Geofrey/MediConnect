package com.example.health_platform.modules.doctor.DTO;

import lombok.Data;

@Data
public class DiagnosisResponseDTO {

    private Long id;
    private Long visitId;
    private String diagnosisText;
    private String treatment;

    
}
