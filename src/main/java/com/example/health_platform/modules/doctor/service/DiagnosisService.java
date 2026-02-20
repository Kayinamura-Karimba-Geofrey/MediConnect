package com.example.health_platform.modules.doctor.service;

import com.example.health_platform.modules.doctor.DTO.DiagnosisRequestDTO;
import com.example.health_platform.modules.doctor.DTO.DiagnosisResponseDTO;

import java.util.List;

public interface DiagnosisService {

    DiagnosisResponseDTO createDiagnosis(DiagnosisRequestDTO dto);

    List<DiagnosisResponseDTO> getDiagnosisByVisit(Long visitId);
}
