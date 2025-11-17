package com.example.health_platform.modules.medicalrecord.DTO;

import lombok.Data;

@Data
public class MedicalRecordResponse {
    private Long id;           // Record ID
    private Long userId;       // User ID
    private String diagnosis;  // Diagnosis details
    private String scanUrl;    // URL of scan image
    private String labResultUrl; // URL of lab result
    private String createdAt;  // Timestamp as String
}
