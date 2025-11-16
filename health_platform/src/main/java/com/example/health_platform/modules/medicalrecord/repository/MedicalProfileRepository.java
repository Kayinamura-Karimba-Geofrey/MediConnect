package com.example.health_platform.modules.medicalrecord.repository;

import com.example.health_platform.modules.medicalrecord.entity.MedicalProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicalProfileRepository extends JpaRepository<MedicalProfile, Long> {
    Optional<MedicalProfile> findByUserId(Long userId);
}
