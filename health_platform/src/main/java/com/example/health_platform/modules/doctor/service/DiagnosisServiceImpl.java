package com.example.health_platform.modules.doctor.service;
import com.example.health_platform.modules.doctor.DTO.DiagnosisRequestDTO;
import com.example.health_platform.modules.doctor.DTO.DiagnosisResponseDTO;
import com.example.health_platform.modules.doctor.model.Diagnosis;
import com.example.health_platform.modules.doctor.model.Visit;
import com.example.health_platform.modules.doctor.repository.DiagnosisRepository;
import com.example.health_platform.modules.doctor.repository.VisitRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class DiagnosisServiceImpl implements DiagnosisService {

    private final DiagnosisRepository diagnosisRepository;
    private final VisitRepository visitRepository;

    public DiagnosisServiceImpl(DiagnosisRepository diagnosisRepository, VisitRepository visitRepository) {
        this.diagnosisRepository = diagnosisRepository;
        this.visitRepository = visitRepository;
    }

    @Override
    public DiagnosisResponseDTO createDiagnosis(DiagnosisRequestDTO dto) {

        Visit visit = visitRepository.findById(dto.getVisitId())
                .orElseThrow(() -> new RuntimeException("Visit not found"));

        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setDiagnosisText(dto.getDiagnosisText());
        diagnosis.setTreatment(dto.getTreatment());
        diagnosis.setVisit(visit);

        Diagnosis saved = diagnosisRepository.save(diagnosis);

        DiagnosisResponseDTO response = new DiagnosisResponseDTO();
        response.setId(saved.getId());
        response.setDiagnosisText(saved.getDiagnosisText());
        response.setTreatment(saved.getTreatment());
        response.setVisitId(saved.getVisit().getId());

        return response;
    }

    @Override
    public List<DiagnosisResponseDTO> getDiagnosisByVisit(Long visitId) {

        Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new RuntimeException("Visit not found"));

        return diagnosisRepository.findByVisit(visit)
                .stream()
                .map(d -> {
                    DiagnosisResponseDTO res = new DiagnosisResponseDTO();
                    res.setId(d.getId());
                    res.setDiagnosisText(d.getDiagnosisText());
                    res.setTreatment(d.getTreatment());
                    res.setVisitId(visitId);
                    return res;
                })
                .collect(Collectors.toList());
    }
}
