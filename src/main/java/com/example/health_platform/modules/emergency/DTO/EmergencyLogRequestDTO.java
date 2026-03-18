package com.example.health_platform.modules.emergency.dto;

import lombok.Data;

@Data
public class EmergencyLogRequestDTO {

    private String token;
    private String accessedBy;
    private String status;

    
}

