package com.example.health_platform.modules.medicalrecord.DTO;


import lombok.Data;

@Data
public class MedicalRecordRequest {
    private Long userId;       // ID of the user this record belongs to
    private String diagnosis;  // Diagnosis details
    private String scanUrl;    // URL of scan image
    private String labResultUrl; // URL of lab result
}
