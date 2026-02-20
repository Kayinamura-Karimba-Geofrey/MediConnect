package com.example.health_platform.modules.emergency.service;



import com.example.health_platform.auth.model.User;
import com.example.health_platform.modules.emergency.DTO.EmergencyTokenResponseDTO;
import com.example.health_platform.modules.emergency.DTO.EmergencyProfileDTO;

public interface EmergencyService {

    EmergencyTokenResponseDTO generateToken(User patient);

    EmergencyProfileDTO getEmergencyProfile(String token);

    void logAccess(String token, String accessedBy, String status);
}

