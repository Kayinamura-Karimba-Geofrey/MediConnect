package com.example.health_platform.auth.service;

import com.example.health_platform.auth.dto.JwtResponse;
import com.example.health_platform.auth.dto.LoginRequest;
import com.example.health_platform.auth.dto.RefreshTokenRequest;
import com.example.health_platform.auth.dto.RegisterRequest;
import com.example.health_platform.auth.dto.UserResponse2;
import com.example.health_platform.auth.dto.VerifyRequest;
import com.example.health_platform.auth.model.User;

import jakarta.servlet.http.HttpServletRequest;


public interface AuthService {

    User register(RegisterRequest request);

    String login(LoginRequest request);

    String verifyAccount(VerifyRequest request);

    JwtResponse refreshToken(RefreshTokenRequest request);

    
    User getCurrentUser(HttpServletRequest request);

    UserResponse2 getCurrentUserDto(HttpServletRequest request);
}
