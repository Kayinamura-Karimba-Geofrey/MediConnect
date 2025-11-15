package com.example.health_platform.auth.service;
import com.example.health_platform.auth.model.User;
import com.example.health_platform.auth.model.dto.LoginRequest;
import com.example.health_platform.auth.model.dto.RegisterRequest;

public interface AuthService {
    User register(RegisterRequest request);
    String login(LoginRequest request);
    
}
