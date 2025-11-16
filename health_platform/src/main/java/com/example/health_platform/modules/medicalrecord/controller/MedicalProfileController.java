package com.example.health_platform.modules.medicalrecord.controller;

import com.example.health_platform.auth.security.UserPrincipal;
import com.example.health_platform.modules.medicalrecord.DTO.MedicalProfileRequest;
import com.example.health_platform.modules.medicalrecord.entity.MedicalProfile;
import com.example.health_platform.modules.medicalrecord.service.MedicalProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medical")
@RequiredArgsConstructor
public class MedicalProfileController {

    private final MedicalProfileService medicalProfileService;

    @PostMapping("/profile")
    public ResponseEntity<MedicalProfile> createOrUpdateProfile(
            @AuthenticationPrincipal UserPrincipal currentUser,
            @RequestBody MedicalProfileRequest req
    ) {
        MedicalProfile profile =
                medicalProfileService.createOrUpdate(currentUser.getId(), req);

        return ResponseEntity.ok(profile);
    }
}
