package com.example.health_platform.modules.visit.DTO;

import lombok.Data;

@Data
public class CreateVisitDTO {
    private Long patientId;
    private String reason;
}
