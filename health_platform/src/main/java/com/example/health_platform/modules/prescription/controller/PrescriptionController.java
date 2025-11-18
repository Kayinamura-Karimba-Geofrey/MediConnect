package com.example.health_platform.modules.prescription.controller;

import com.example.health_platform.auth.model.User;
import com.example.health_platform.modules.prescription.DTO.CreatePrescriptionDTO;
import com.example.health_platform.modules.prescription.DTO.PrescriptionResponseDTO;
import com.example.health_platform.modules.prescription.service.PrescriptionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prescription")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @PostMapping("/create")
    public PrescriptionResponseDTO createPrescription(
            @AuthenticationPrincipal User doctor,
            @RequestBody CreatePrescriptionDTO dto
    ) {
        return prescriptionService.createPrescription(doctor, dto);
    }

    @GetMapping("/{id}")
    public PrescriptionResponseDTO getPrescription(@PathVariable Long id) {
        return prescriptionService.getPrescriptionById(id);
    }

    @GetMapping("/patient/{patientId}")
    public List<PrescriptionResponseDTO> getPrescriptionsByPatient(@PathVariable Long patientId) {
        return prescriptionService.getPrescriptionsByPatient(patientId);
    }

    @PatchMapping("/dispense/{id}")
    public PrescriptionResponseDTO markAsDispensed(@PathVariable Long id) {
        return prescriptionService.markAsDispensed(id);
    }
}
