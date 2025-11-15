package com.example.health_platform.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import com.example.health_platform.auth.service.AuthService;

import jakarta.validation.Valid;

import com.example.health_platform.auth.model.User;
import com.example.health_platform.auth.model.dto.JwtResponse;
import com.example.health_platform.auth.model.dto.LoginRequest;
import com.example.health_platform.auth.model.dto.RefreshTokenRequest;
import com.example.health_platform.auth.model.dto.RegisterRequest;
import com.example.health_platform.auth.model.dto.UserResponse2;
import com.example.health_platform.auth.model.dto.VerifyRequest;

import jakarta.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody RegisterRequest request) {
        User registeredUser = authService.register(request);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        String token = authService.login(request);
        return ResponseEntity.ok(new JwtResponse(token));
    }
    @PostMapping("/verify")
    public ResponseEntity<String> verifyAccount(@Valid @RequestBody VerifyRequest request) {
        String response = authService.verifyAccount(request);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        JwtResponse response = authService.refreshToken(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse2> getCurrentUser(HttpServletRequest request) {
        UserResponse2 user = authService.getCurrentUser(request);
        return ResponseEntity.ok(user);
    }

}
