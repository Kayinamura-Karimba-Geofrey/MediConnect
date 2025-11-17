package com.example.health_platform.modules.medicalrecord.DTO;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecordResponse {

    private Long id;
    private Long patientId;
    private Long doctorId;

    private String diagnosis;
    private String scanUrl;
    private String labResult;
    private String notes;

    private String createdAt;
}
