package com.example.health_platform.modules.doctor.repository;



import com.example.health_platform.modules.doctor.model.Prescription1;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<Prescription1, Long> {
}
