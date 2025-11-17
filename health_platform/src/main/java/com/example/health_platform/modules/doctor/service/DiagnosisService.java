package com.example.health_platform.modules.doctor.service;



import com.example.health_platform.auth.model.User;
import com.example.health_platform.modules.doctor.DTO.DiagnosisRequestDTO;
import com.example.health_platform.modules.doctor.model.Diagnosis;

import java.util.List;

public interface DiagnosisService {
    Diagnosis createDiagnosis(User doctor, DiagnosisRequestDTO dto);
    List<Diagnosis> getDiagnosisByVisitId(Long visitId);
}
