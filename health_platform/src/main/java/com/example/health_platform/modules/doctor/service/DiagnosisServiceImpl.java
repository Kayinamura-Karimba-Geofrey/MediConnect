package com.example.health_platform.modules.doctor.service;



import com.example.health_platform.auth.model.User;
import com.example.health_platform.modules.doctor.DTO.DiagnosisRequestDTO;
import com.example.health_platform.modules.doctor.model.Diagnosis;

import com.example.health_platform.modules.doctor.repository.DiagnosisRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosisServiceImpl implements DiagnosisService {

    private final DiagnosisRepository diagnosisRepository;
    

    public DiagnosisServiceImpl(DiagnosisRepository diagnosisRepository
                                ) {
        this.diagnosisRepository = diagnosisRepository;
        
    }

    @Override
    public Diagnosis createDiagnosis(User doctor, DiagnosisRequestDTO dto) {
        

        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setDoctor(doctor);
        
        diagnosis.setDiagnosis(dto.getDiagnosis());
        diagnosis.setTreatment(dto.getTreatment());

        return diagnosisRepository.save(diagnosis);
    }

    @Override
    public List<Diagnosis> getDiagnosisByVisitId(Long visitId) {
        return diagnosisRepository.findByVisitId(visitId);
    }
}
