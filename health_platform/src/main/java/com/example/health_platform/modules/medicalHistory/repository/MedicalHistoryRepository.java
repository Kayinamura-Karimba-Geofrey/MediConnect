package com.example.health_platform.modules.medicalHistory.repository;
import com.example.health_platform.modules.medicalHistory.model.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Long> {
    List<MedicalHistory> findByPatientName(String patientName);
}
