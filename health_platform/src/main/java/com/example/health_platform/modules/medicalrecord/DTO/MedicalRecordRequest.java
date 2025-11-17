package com.example.health_platform.modules.medicalrecord.DTO;



import lombok.Data;

@Data
public class MedicalRecordRequest {
    private Long patientId;
    private String diagnosis;
    private String scanUrl;
    private String labResult;
    private String notes;
}

