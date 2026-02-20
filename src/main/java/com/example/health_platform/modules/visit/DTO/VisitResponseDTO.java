package com.example.health_platform.modules.visit.DTO;

import lombok.Data;

@Data
public class VisitResponseDTO {
    private Long id;
    private Long doctorId;
    private Long patientId;
    private String reason;
    private String createdAt;
}
