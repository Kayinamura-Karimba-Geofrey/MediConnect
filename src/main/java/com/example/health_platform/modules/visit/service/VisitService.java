package com.example.health_platform.modules.visit.service;

import com.example.health_platform.auth.model.User;
import com.example.health_platform.modules.visit.DTO.CreateVisitDTO;
import com.example.health_platform.modules.visit.DTO.VisitResponseDTO;

import java.util.List;

public interface VisitService {

    VisitResponseDTO createVisit(User doctor, CreateVisitDTO dto);

    List<VisitResponseDTO> getVisitsForPatient(Long patientId);

    List<VisitResponseDTO> getVisitsForDoctor(Long doctorId);

    VisitResponseDTO getVisitById(Long id);
}
