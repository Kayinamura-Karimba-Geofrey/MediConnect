package com.example.health_platform.modules.prescription.service;

import com.example.health_platform.auth.model.User;
import com.example.health_platform.modules.prescription.DTO.CreatePrescriptionDTO;
import com.example.health_platform.modules.prescription.DTO.PrescriptionResponseDTO;

import java.util.List;

public interface PrescriptionService {

    PrescriptionResponseDTO createPrescription(User doctor, CreatePrescriptionDTO dto);

    PrescriptionResponseDTO getPrescriptionById(Long id);

    List<PrescriptionResponseDTO> getPrescriptionsByPatient(Long patientId);

    PrescriptionResponseDTO markAsDispensed(Long prescriptionId);
}
