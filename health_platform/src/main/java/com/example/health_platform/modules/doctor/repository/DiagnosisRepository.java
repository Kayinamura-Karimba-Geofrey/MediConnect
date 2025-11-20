package com.example.health_platform.modules.doctor.repository;


import com.example.health_platform.modules.doctor.model.Diagnosis;
import com.example.health_platform.modules.doctor.model.Visit1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {
    List<Diagnosis> findByVisit(Visit1 visit);
}
