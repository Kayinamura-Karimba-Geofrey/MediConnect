package com.example.health_platform.auth.repository;

import com.example.health_platform.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.health_platform.auth.model.Role  ;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    long countByRole(Role role);

@Query("SELECT COUNT(u) FROM User u WHERE u.role = 'DOCTOR' AND u.approved = true")
long countApprovedDoctors();


    
}
