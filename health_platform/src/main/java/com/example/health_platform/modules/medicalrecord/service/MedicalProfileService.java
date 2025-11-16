package com.example.health_platform.modules.medicalrecord.service;

import com.example.health_platform.modules.medicalrecord.DTO.MedicalProfileRequest;
import com.example.health_platform.modules.medicalrecord.model.MedicalProfile;
import com.example.health_platform.modules.medicalrecord.repository.MedicalProfileRepository;
import com.example.health_platform.auth.model.User;
import com.example.health_platform.modules.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicalProfileService {

    private final MedicalProfileRepository profileRepository;
    private final UserService userService;

    public MedicalProfile createOrUpdate(Long userId, MedicalProfileRequest req) {

        // Get user by ID
        User user = userService.getUserById(userId);

        // Find existing profile or create new
        MedicalProfile profile = profileRepository.findByUserId(userId)
                .orElse(new MedicalProfile());

        profile.setUser(user);
        profile.setBloodGroup(req.getBloodGroup());
        profile.setAllergies(req.getAllergies());
        profile.setChronicDiseases(req.getChronicDiseases());
        profile.setMedications(req.getMedications());

        return profileRepository.save(profile);
    }
}
