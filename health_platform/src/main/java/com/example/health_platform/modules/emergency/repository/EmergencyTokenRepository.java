package com.example.health_platform.modules.emergency.repository;


import com.example.health_platform.modules.emergency.model.EmergencyToken;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EmergencyTokenRepository extends JpaRepository<EmergencyToken, Long> {
    Optional<EmergencyToken> findByToken(String token);
}
