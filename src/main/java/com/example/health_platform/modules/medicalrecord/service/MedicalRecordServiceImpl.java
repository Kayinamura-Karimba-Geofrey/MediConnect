package com.example.health_platform.modules.medicalrecord.service;

import com.example.health_platform.modules.medicalrecord.DTO.MedicalRecordRequest;
import com.example.health_platform.modules.medicalrecord.DTO.MedicalRecordResponse;
import com.example.health_platform.modules.medicalrecord.model.MedicalRecord;
import com.example.health_platform.modules.medicalrecord.model.MedicalProfile;
import com.example.health_platform.modules.medicalrecord.repository.MedicalRecordRepository;
import com.example.health_platform.modules.medicalrecord.repository.MedicalProfileRepository;
import com.example.health_platform.auth.model.User;
import com.example.health_platform.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final MedicalProfileRepository medicalProfileRepository;
    private final UserRepository userRepository;

    @Override
    public MedicalRecordResponse createOrUpdateRecord(MedicalRecordRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        MedicalRecord record = new MedicalRecord();
        record.setUser(user);
        record.setDiagnosis(request.getDiagnosis());
        record.setScanUrl(request.getScanUrl());
        record.setLabResultUrl(request.getLabResultUrl());

        medicalRecordRepository.save(record);

        MedicalRecordResponse response = new MedicalRecordResponse();
        response.setId(record.getId());
        response.setUserId(user.getId());
        response.setDiagnosis(record.getDiagnosis());
        response.setScanUrl(record.getScanUrl());
        response.setLabResultUrl(record.getLabResultUrl());
        response.setCreatedAt(record.getCreatedAt().toString());

        return response;
    }

    @Override
    public MedicalRecordResponse getRecordById(Long recordId) {
        MedicalRecord record = medicalRecordRepository.findById(recordId)
                .orElseThrow(() -> new RuntimeException("Record not found"));

        MedicalRecordResponse response = new MedicalRecordResponse();
        response.setId(record.getId());
        response.setUserId(record.getUser().getId());
        response.setDiagnosis(record.getDiagnosis());
        response.setScanUrl(record.getScanUrl());
        response.setLabResultUrl(record.getLabResultUrl());
        response.setCreatedAt(record.getCreatedAt().toString());

        return response;
    }

    @Override
    public List<MedicalRecordResponse> listRecordsByUser(Long userId) {
        List<MedicalRecord> records = medicalRecordRepository.findByUserId(userId);

        return records.stream().map(record -> {
            MedicalRecordResponse dto = new MedicalRecordResponse();
            dto.setId(record.getId());
            dto.setUserId(record.getUser().getId());
            dto.setDiagnosis(record.getDiagnosis());
            dto.setScanUrl(record.getScanUrl());
            dto.setLabResultUrl(record.getLabResultUrl());
            dto.setCreatedAt(record.getCreatedAt().toString());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public Object getMedicalProfileByUser(Long userId) {
        MedicalProfile profile = medicalProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Medical profile not found"));
        return profile;
    }

    @Override
    public MedicalRecord getMedicalRecordById(Long recordId) {
        return medicalRecordRepository.findById(recordId)
                .orElseThrow(() -> new RuntimeException("Medical record not found"));
    }

    @Override
    public void deleteMedicalRecord(Long recordId) {
        MedicalRecord record = medicalRecordRepository.findById(recordId)
                .orElseThrow(() -> new RuntimeException("Medical record not found"));
        medicalRecordRepository.delete(record);
    }
}
