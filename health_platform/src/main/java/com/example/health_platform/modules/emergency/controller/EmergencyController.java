package com.example.health_platform.modules.emergency.controller;



import com.example.health_platform.auth.model.User;
import com.example.health_platform.modules.emergency.DTO.EmergencyLogRequestDTO;
import com.example.health_platform.modules.emergency.DTO.EmergencyProfileDTO;
import com.example.health_platform.modules.emergency.DTO.EmergencyTokenResponseDTO;
import com.example.health_platform.modules.emergency.service.EmergencyService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emergency")
public class EmergencyController {

    private final EmergencyService emergencyService;

    public EmergencyController(EmergencyService emergencyService) {
        this.emergencyService = emergencyService;
    }

    @PostMapping("/generate-token")
    public EmergencyTokenResponseDTO generateToken(@AuthenticationPrincipal User patient) {
        return emergencyService.generateToken(patient);
    }

    @GetMapping("/profile/{token}")
    public EmergencyProfileDTO getProfile(@PathVariable String token) {
        return emergencyService.getEmergencyProfile(token);
    }

    @PostMapping("/log")
    public String logAccess(@RequestBody EmergencyLogRequestDTO dto) {
        emergencyService.logAccess(dto.getToken(), dto.getAccessedBy(), dto.getStatus());
        return "Log saved";
    }
}

