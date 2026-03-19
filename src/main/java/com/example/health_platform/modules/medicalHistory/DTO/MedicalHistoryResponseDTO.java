package com.example.health_platform.modules.medicalhistory.dto;


import java.time.LocalDate;
import lombok.Data;
@Data

public class MedicalHistoryResponseDTO {
    private Long id;
    private Long patientId;
    private LocalDate recordDate;
    private String diagnosis;
    private String treatment;
    private String notes;
}
