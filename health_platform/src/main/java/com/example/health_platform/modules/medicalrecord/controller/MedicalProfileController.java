package com.example.health_platform.modules.medicalrecord.controller;
import com.example.health_platform.modules.medicalrecord.DTO.MedicalProfileRequest;
import com.example.health_platform.modules.medicalrecord.DTO.MedicalProfileResponse;
import com.example.health_platform.modules.medicalrecord.service.MedicalProfileService;
import com.example.health_platform.auth.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medical/profile")
@RequiredArgsConstructor
public class MedicalProfileController {

    private final MedicalProfileService medicalProfileService;

    @PostMapping
    public MedicalProfileResponse createOrUpdate(
            @CurrentUser Long currentUserId,
            @RequestBody MedicalProfileRequest request) {

        return medicalProfileService.createOrUpdate(currentUserId, request);
    }

    @GetMapping("/{userId}")
    public MedicalProfileResponse getByUserId(@PathVariable Long userId) {
        return medicalProfileService.getByUserId(userId);
    }
}
