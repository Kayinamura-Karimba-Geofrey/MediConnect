package com.example.health_platform.modules.user.repository;

import com.example.health_platform.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.health_platform.auth.model.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository2 extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    long countByRole(Role role);

}
