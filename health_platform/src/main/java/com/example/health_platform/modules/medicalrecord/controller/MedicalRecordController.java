package com.example.health_platform.modules.medicalrecord.controller;

import com.example.health_platform.auth.security.CurrentUser;
import com.example.health_platform.modules.medicalrecord.DTO.MedicalRecordRequest;
import com.example.health_platform.modules.medicalrecord.DTO.MedicalRecordResponse;
import com.example.health_platform.modules.medicalrecord.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medical/record")
@RequiredArgsConstructor
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    @PostMapping("/create")
    public MedicalRecordResponse createRecord(
            @CurrentUser Long doctorId,
            @RequestBody MedicalRecordRequest request
    ) {
        return medicalRecordService.createRecord(doctorId, request);
    }
}
