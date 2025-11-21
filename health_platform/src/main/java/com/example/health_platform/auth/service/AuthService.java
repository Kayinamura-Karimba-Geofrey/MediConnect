package com.example.health_platform.auth.service;

import com.example.health_platform.auth.dto.JwtResponse;
import com.example.health_platform.auth.dto.LoginRequest;
import com.example.health_platform.auth.dto.RefreshTokenRequest;
import com.example.health_platform.auth.dto.RegisterRequest;
import com.example.health_platform.auth.dto.UserResponse2;
import com.example.health_platform.auth.dto.VerifyRequest;
import com.example.health_platform.auth.model.User;

import jakarta.servlet.http.HttpServletRequest;
//import java.util.Optional;

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
