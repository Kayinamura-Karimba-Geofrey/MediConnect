package com.example.health_platform.modules.medicalrecord.service;

import com.example.health_platform.modules.medicalrecord.DTO.MedicalRecordRequest;
import com.example.health_platform.modules.medicalrecord.DTO.MedicalRecordResponse;

import java.util.List;

public interface MedicalRecordService {

    MedicalRecordResponse createOrUpdateRecord(MedicalRecordRequest request);

    MedicalRecordResponse getRecordById(Long recordId);

    List<MedicalRecordResponse> listRecordsByUser(Long userId);

    Object getMedicalProfileByUser(Long userId);
}
