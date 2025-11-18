package com.example.health_platform.modules.prescription.repository;

import com.example.health_platform.modules.doctor.model.Prescription;
import com.example.health_platform.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    List<Prescription> findByPatient(User patient);
}
