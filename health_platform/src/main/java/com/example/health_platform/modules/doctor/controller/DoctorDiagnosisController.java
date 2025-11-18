package com.example.health_platform.modules.doctor.controller;

import com.example.health_platform.modules.doctor.DTO.DiagnosisRequestDTO;
import com.example.health_platform.modules.doctor.DTO.DiagnosisResponseDTO;
import com.example.health_platform.modules.doctor.service.DiagnosisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor/diagnosis")
public class DoctorDiagnosisController {

    private final DiagnosisService diagnosisService;

    public DoctorDiagnosisController(DiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }

    @PostMapping("/create")
    public ResponseEntity<DiagnosisResponseDTO> createDiagnosis(
            @RequestBody DiagnosisRequestDTO dto
    ) {
        return ResponseEntity.ok(diagnosisService.createDiagnosis(dto));
    }

    @GetMapping("/visit/{visitId}")
    public ResponseEntity<List<DiagnosisResponseDTO>> getDiagnosisByVisit(
            @PathVariable Long visitId
    ) {
        return ResponseEntity.ok(diagnosisService.getDiagnosisByVisit(visitId));
    }
}
