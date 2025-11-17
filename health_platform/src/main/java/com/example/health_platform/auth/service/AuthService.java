package com.example.health_platform.auth.service;

import com.example.health_platform.auth.model.User;
import com.example.health_platform.auth.model.dto.JwtResponse;
import com.example.health_platform.auth.model.dto.LoginRequest;
import com.example.health_platform.auth.model.dto.RefreshTokenRequest;
import com.example.health_platform.auth.model.dto.RegisterRequest;
import com.example.health_platform.auth.model.dto.UserResponse2;
import com.example.health_platform.auth.model.dto.VerifyRequest;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface AuthService {

    User register(RegisterRequest request);

    String login(LoginRequest request);

    String verifyAccount(VerifyRequest request);

    JwtResponse refreshToken(RefreshTokenRequest request);

    // Returns User entity for service use (appointments, medical records, etc.)
    User getCurrentUser(HttpServletRequest request);

    // Returns DTO for controller responses (/auth/me)
    UserResponse2 getCurrentUserDto(HttpServletRequest request);
}
