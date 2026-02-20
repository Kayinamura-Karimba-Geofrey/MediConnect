package com.example.health_platform.modules.medicalrecord.repository;

import com.example.health_platform.modules.medicalrecord.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {

    
    List<MedicalRecord> findByUserId(Long userId);
}
