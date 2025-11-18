package com.example.health_platform.modules.doctor.repository;



import com.example.health_platform.modules.doctor.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit, Long> {
}
