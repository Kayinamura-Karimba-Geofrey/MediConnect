package com.example.health_platform.modules.visit.repository;

import com.example.health_platform.modules.visit.model.Visit;
import com.example.health_platform.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

    List<Visit> findByPatient(User patient);
    List<Visit> findByDoctor(User doctor);

    long countByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}
