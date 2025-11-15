package com.example.health_platform.auth.model.dto;

import lombok.Data;

@Data
public class VerifyRequest {
    private String email;
    private String verificationCode;
    
}
