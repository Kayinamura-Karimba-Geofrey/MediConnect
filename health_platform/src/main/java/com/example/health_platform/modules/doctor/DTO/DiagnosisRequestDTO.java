package com.example.health_platform.modules.doctor.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class DiagnosisRequestDTO {

    @NotNull
    private Long visitId;

    @NotBlank
    private String diagnosisText;

    @NotBlank
    private String treatment;

    
}
