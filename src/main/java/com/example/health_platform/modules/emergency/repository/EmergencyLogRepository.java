package com.example.health_platform.modules.emergency.repository;



import com.example.health_platform.modules.emergency.model.EmergencyLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergencyLogRepository extends JpaRepository<EmergencyLog, Long> {
}
