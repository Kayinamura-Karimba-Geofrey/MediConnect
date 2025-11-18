package com.example.health_platform.modules.emergency.service;



import com.example.health_platform.auth.model.User;
import com.example.health_platform.modules.emergency.DTO.EmergencyTokenResponseDTO;
import com.example.health_platform.modules.emergency.DTO.EmergencyProfileDTO;
import com.example.health_platform.modules.emergency.model.EmergencyLog;
import com.example.health_platform.modules.emergency.model.EmergencyToken;
import com.example.health_platform.modules.emergency.repository.EmergencyLogRepository;
import com.example.health_platform.modules.emergency.repository.EmergencyTokenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class EmergencyServiceImpl implements EmergencyService {

    private final EmergencyTokenRepository tokenRepo;
    private final EmergencyLogRepository logRepo;

    public EmergencyServiceImpl(EmergencyTokenRepository tokenRepo, EmergencyLogRepository logRepo) {
        this.tokenRepo = tokenRepo;
        this.logRepo = logRepo;
    }

    @Override
    public EmergencyTokenResponseDTO generateToken(User patient) {

        EmergencyToken token = new EmergencyToken();
        token.setPatient(patient);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiresAt(LocalDateTime.now().plusMinutes(10));

        EmergencyToken saved = tokenRepo.save(token);

        EmergencyTokenResponseDTO dto = new EmergencyTokenResponseDTO();
        dto.setToken(saved.getToken());
        dto.setExpiresAt(saved.getExpiresAt().toString());

        return dto;
    }

    @Override
    public EmergencyProfileDTO getEmergencyProfile(String token) {

        EmergencyToken key = tokenRepo.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        if (key.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expired");
        }

        User patient = key.getPatient();

        EmergencyProfileDTO profile = new EmergencyProfileDTO();
        profile.setFullName(patient.getFullName());
        profile.setBloodGroup(patient.getBloodGroup());
        profile.setAllergies(patient.getAllergies());
        profile.setChronicDiseases(patient.getChronicDiseases());
        profile.setEmergencyContact(patient.getEmergencyContact());

        return profile;
    }

    @Override
    public void logAccess(String token, String accessedBy, String status) {

        EmergencyLog log = new EmergencyLog();
        log.setToken(token);
        log.setAccessedBy(accessedBy);
        log.setStatus(status);

        logRepo.save(log);
    }
}

