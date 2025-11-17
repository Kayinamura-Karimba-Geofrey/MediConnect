package com.example.health_platform.modules.medicalrecord.service;

import com.example.health_platform.modules.medicalrecord.DTO.MedicalRecordRequest;
import com.example.health_platform.modules.medicalrecord.DTO.MedicalRecordResponse;
import com.example.health_platform.modules.medicalrecord.model.MedicalRecord;
import com.example.health_platform.modules.medicalrecord.repository.MedicalRecordRepository;

import com.example.health_platform.auth.model.User;
import com.example.health_platform.auth.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final UserRepository userRepository;

    public MedicalRecordResponse createRecord(Long doctorId, MedicalRecordRequest request) {

        User patient = userRepository.findById(request.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        User doctor = userRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        MedicalRecord record = MedicalRecord.builder()
                .patient(patient)
                .doctor(doctor)
                .diagnosis(request.getDiagnosis())
                .scanUrl(request.getScanUrl())
                .labResult(request.getLabResult())
                .notes(request.getNotes())
                .createdAt(LocalDateTime.now())
                .build();

        medicalRecordRepository.save(record);

        return toResponse(record);
    }

    private MedicalRecordResponse toResponse(MedicalRecord record) {
        return MedicalRecordResponse.builder()
                .id(record.getId())
                .patientId(record.getPatient().getId())
                .doctorId(record.getDoctor().getId())
                .diagnosis(record.getDiagnosis())
                .scanUrl(record.getScanUrl())
                .labResult(record.getLabResult())
                .notes(record.getNotes())
                .createdAt(record.getCreatedAt().toString())
                .build();
    }
}
