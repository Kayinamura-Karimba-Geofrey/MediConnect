package com.example.health_platform.modules.medicalrecord.DTO;



import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicalProfileResponse {

    private Long id;
    private Long userId;
    private String bloodGroup;
    private String allergies;
    private String chronicDiseases;
    private String medications;
    private String emergencyContactName;
    private String emergencyContactPhone;
}
