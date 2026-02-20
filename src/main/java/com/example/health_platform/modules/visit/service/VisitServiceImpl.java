package com.example.health_platform.modules.visit.service;

import com.example.health_platform.auth.model.User;
import com.example.health_platform.auth.repository.UserRepository;
import com.example.health_platform.modules.visit.DTO.CreateVisitDTO;
import com.example.health_platform.modules.visit.DTO.VisitResponseDTO;
import com.example.health_platform.modules.visit.model.Visit;
import com.example.health_platform.modules.visit.repository.VisitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitServiceImpl implements VisitService {

    private final VisitRepository visitRepository;
    private final UserRepository userRepository;

    public VisitServiceImpl(VisitRepository visitRepository,
                            UserRepository userRepository) {
        this.visitRepository = visitRepository;
        this.userRepository = userRepository;
    }

    @Override
    public VisitResponseDTO createVisit(User doctor, CreateVisitDTO dto) {

        User patient = userRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Visit visit = new Visit();
        visit.setDoctor(doctor);
        visit.setPatient(patient);
        visit.setReason(dto.getReason());

        Visit saved = visitRepository.save(visit);

        return mapToDTO(saved);
    }

    @Override
    public List<VisitResponseDTO> getVisitsForPatient(Long patientId) {
        User patient = userRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        return visitRepository.findByPatient(patient)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<VisitResponseDTO> getVisitsForDoctor(Long doctorId) {
        User doctor = userRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        return visitRepository.findByDoctor(doctor)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VisitResponseDTO getVisitById(Long id) {
        Visit visit = visitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visit not found"));
        return mapToDTO(visit);
    }

    private VisitResponseDTO mapToDTO(Visit visit) {
        VisitResponseDTO dto = new VisitResponseDTO();
        dto.setId(visit.getId());
        dto.setDoctorId(visit.getDoctor().getId());
        dto.setPatientId(visit.getPatient().getId());
        dto.setReason(visit.getReason());
        dto.setCreatedAt(visit.getCreatedAt().toString());
        return dto;
    }
}
