package com.example.health_platform.modules.visit.controller;

import com.example.health_platform.auth.model.User;
import com.example.health_platform.modules.visit.DTO.CreateVisitDTO;
import com.example.health_platform.modules.visit.DTO.VisitResponseDTO;
import com.example.health_platform.modules.visit.service.VisitService;
import com.example.health_platform.auth.security.CurrentUser;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visit")
public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @PostMapping("/create")
    public VisitResponseDTO createVisit(@CurrentUser User doctor,
                                        @RequestBody CreateVisitDTO dto) {
        return visitService.createVisit(doctor, dto);
    }

    @GetMapping("/{id}")
    public VisitResponseDTO getVisit(@PathVariable Long id) {
        return visitService.getVisitById(id);
    }

    @GetMapping("/patient/{id}")
    public List<VisitResponseDTO> getPatientVisits(@PathVariable Long id) {
        return visitService.getVisitsForPatient(id);
    }

    @GetMapping("/doctor/{id}")
    public List<VisitResponseDTO> getDoctorVisits(@PathVariable Long id) {
        return visitService.getVisitsForDoctor(id);
    }
}
