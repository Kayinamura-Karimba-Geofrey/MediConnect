package com.example.health_platform.modules.medicalrecord.service;
import com.example.health_platform.auth.model.User;
import com.example.health_platform.auth.repository.UserRepository;
import com.example.health_platform.modules.medicalrecord.DTO.MedicalProfileRequest;
import com.example.health_platform.modules.medicalrecord.DTO.MedicalProfileResponse;
import com.example.health_platform.modules.medicalrecord.exception.MedicalProfileNotFoundException;
import com.example.health_platform.modules.medicalrecord.model.MedicalProfile;

import com.example.health_platform.modules.medicalrecord.repository.MedicalProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MedicalProfileService {

    private final MedicalProfileRepository medicalProfileRepository;
    private final UserRepository userRepository;

    public MedicalProfileResponse createOrUpdate(Long userId, MedicalProfileRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        MedicalProfile profile = medicalProfileRepository
                .findByUserId(userId)
                .orElse(MedicalProfile.builder().user(user).build());

        profile.setBloodGroup(request.getBloodGroup());
        profile.setAllergies(request.getAllergies());
        profile.setChronicDiseases(request.getChronicDiseases());
        profile.setMedications(request.getMedications());
        profile.setEmergencyContactName(request.getEmergencyContactName());
        profile.setEmergencyContactPhone(request.getEmergencyContactPhone());

        medicalProfileRepository.save(profile);

        return toResponse(profile);
    }

    public MedicalProfileResponse getByUserId(Long userId) {

        MedicalProfile profile = medicalProfileRepository
                .findByUserId(userId)
                .orElseThrow(() -> new MedicalProfileNotFoundException("Medical profile not found"));

        return toResponse(profile);
    }

    private MedicalProfileResponse toResponse(MedicalProfile profile) {
        return MedicalProfileResponse.builder()
                .id(profile.getId())
                .userId(profile.getUser().getId())
                .bloodGroup(profile.getBloodGroup())
                .allergies(profile.getAllergies())
                .chronicDiseases(profile.getChronicDiseases())
                .medications(profile.getMedications())
                .emergencyContactName(profile.getEmergencyContactName())
                .emergencyContactPhone(profile.getEmergencyContactPhone())
                .build();
    }
}
