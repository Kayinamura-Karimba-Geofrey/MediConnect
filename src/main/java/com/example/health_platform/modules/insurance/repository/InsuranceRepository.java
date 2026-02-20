package com.example.health_platform.modules.insurance.repository;



import com.example.health_platform.auth.model.User;
import com.example.health_platform.modules.insurance.model.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
    List<Insurance> findByUser(User user);
}
