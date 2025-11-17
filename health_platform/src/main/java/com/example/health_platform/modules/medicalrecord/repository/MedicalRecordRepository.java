package com.example.health_platform.modules.medicalrecord.repository;



import com.example.health_platform.modules.medicalrecord.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
}
