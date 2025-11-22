package com.example.health_platform.modules.medicalHistory.DTO ;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
@Data
public class MedicalHistoryCreateDTO {

    @NotNull
    private Long patientId;

    @NotNull
    private LocalDate recordDate;

    @NotBlank
    private String diagnosis;

    private String treatment;
    private String notes;

    

    
}
