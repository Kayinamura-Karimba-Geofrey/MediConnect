package com.example.health_platform.modules.visit.dto;

import lombok.Data;

@Data
public class CreateVisitDTO {
    private Long patientId;
    private String reason;
}
