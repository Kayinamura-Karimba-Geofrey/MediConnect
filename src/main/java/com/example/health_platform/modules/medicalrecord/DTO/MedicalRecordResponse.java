package com.example.health_platform.modules.medicalrecord.dto;

import lombok.Data;

@Data
public class MedicalRecordResponse {
    private Long id;           
    private Long userId;       
    private String diagnosis;  
    private String scanUrl;    
    private String labResultUrl; 
    private String createdAt;  
}
