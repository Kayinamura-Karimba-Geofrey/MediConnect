package com.example.health_platform.modules.medicalrecord.DTO;

import lombok.Data;

@Data
public class MedicalProfileRequest {
    private String bloodGroup;
    private String allergies;
    private String chronicDiseases;
    private String medications;
}
