package com.example.health_platform.modules.medicalHistory.service;

import com.example.health_platform.modules.medicalHistory.model.MedicalHistory;
import com.example.health_platform.modules.medicalHistory.repository.MedicalHistoryRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class MedicalHistoryService {

    private final MedicalHistoryRepository repository;

    public MedicalHistoryService(MedicalHistoryRepository repository) {
        this.repository = repository;
    }

    public MedicalHistory save(MedicalHistory history) {
        return repository.save(history);
    }

    public List<MedicalHistory> getAll() {
        return repository.findAll();
    }

    public Optional<MedicalHistory> getById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<MedicalHistory> getByPatientName(String patientName) {
        return repository.findByPatientName(patientName);
    }
}
