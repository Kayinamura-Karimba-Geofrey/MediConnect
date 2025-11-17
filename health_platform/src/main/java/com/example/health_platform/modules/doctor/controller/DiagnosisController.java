package com.example.health_platform.modules.doctor.controller;



import com.example.health_platform.auth.model.User;
import com.example.health_platform.auth.service.AuthService;
import com.example.health_platform.modules.doctor.DTO.DiagnosisRequestDTO;
import com.example.health_platform.modules.doctor.model.Diagnosis;
import com.example.health_platform.modules.doctor.service.DiagnosisService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor/diagnosis")
public class DiagnosisController {

    private final DiagnosisService diagnosisService;
    private final AuthService authService;

    public DiagnosisController(DiagnosisService diagnosisService, AuthService authService) {
        this.diagnosisService = diagnosisService;
        this.authService = authService;
    }

    @PostMapping("/create")
    public ResponseEntity<Diagnosis> createDiagnosis(@RequestBody DiagnosisRequestDTO dto,
                                                     HttpServletRequest request) {
        User doctor = authService.getCurrentUser(request);
        Diagnosis diagnosis = diagnosisService.createDiagnosis(doctor, dto);
        return ResponseEntity.ok(diagnosis);
    }

    @GetMapping("/visit/{visitId}")
    public ResponseEntity<List<Diagnosis>> getDiagnosisByVisit(@PathVariable Long visitId) {
        List<Diagnosis> diagnosisList = diagnosisService.getDiagnosisByVisitId(visitId);
        return ResponseEntity.ok(diagnosisList);
    }
}
