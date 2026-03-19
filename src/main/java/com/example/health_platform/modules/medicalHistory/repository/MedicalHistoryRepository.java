package com.example.health_platform.modules.medicalhistory.repository;
import com.example.health_platform.modules.medicalhistory.model.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Long> {
    List<MedicalHistory> findByPatientName(String patientName);
}
