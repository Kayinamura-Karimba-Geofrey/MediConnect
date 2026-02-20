package com.example.health_platform.modules.emergency.DTO;

import lombok.Data;

@Data
public class EmergencyLogRequestDTO {

    private String token;
    private String accessedBy;
    private String status;

    
}

