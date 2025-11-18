package com.example.health_platform.modules.prescription.service;

import com.example.health_platform.auth.model.User;
import com.example.health_platform.auth.repository.UserRepository;
import com.example.health_platform.modules.prescription.DTO.CreatePrescriptionDTO;
import com.example.health_platform.modules.prescription.DTO.PrescriptionResponseDTO;
import com.example.health_platform.modules.prescription.model.Prescription;
import com.example.health_platform.modules.prescription.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final UserRepository userRepository;

    public PrescriptionServiceImpl(PrescriptionRepository prescriptionRepository,
                                   UserRepository userRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PrescriptionResponseDTO createPrescription(User doctor, CreatePrescriptionDTO dto) {
        User patient = userRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Prescription prescription = new Prescription();
        prescription.setDoctor(doctor);
        prescription.setPatient(patient);
        prescription.setMedicines(dto.getMedicines());
        prescription.setNotes(dto.getNotes());

        Prescription saved = prescriptionRepository.save(prescription);
        return mapToDTO(saved);
    }

    @Override
    public PrescriptionResponseDTO getPrescriptionById(Long id) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found"));
        return mapToDTO(prescription);
    }

    @Override
    public List<PrescriptionResponseDTO> getPrescriptionsByPatient(Long patientId) {
        User patient = userRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        return prescriptionRepository.findByPatient(patient)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PrescriptionResponseDTO markAsDispensed(Long prescriptionId) {
        Prescription prescription = prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() -> new RuntimeException("Prescription not found"));

        prescription.setDispensed(true);
        Prescription saved = prescriptionRepository.save(prescription);

        return mapToDTO(saved);
    }

    private PrescriptionResponseDTO mapToDTO(Prescription p) {
        PrescriptionResponseDTO dto = new PrescriptionResponseDTO();
        dto.setId(p.getId());
        dto.setDoctorId(p.getDoctor().getId());
        dto.setPatientId(p.getPatient().getId());
        dto.setMedicines(p.getMedicines());
        dto.setNotes(p.getNotes());
        dto.setDispensed(p.isDispensed());
        return dto;
    }
}
