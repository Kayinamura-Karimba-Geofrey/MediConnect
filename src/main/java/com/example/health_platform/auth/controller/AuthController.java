package com.example.health_platform.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import com.example.health_platform.auth.dto.JwtResponse;
import com.example.health_platform.auth.dto.LoginRequest;
import com.example.health_platform.auth.dto.RefreshTokenRequest;
import com.example.health_platform.auth.dto.RegisterRequest;
import com.example.health_platform.auth.dto.UserResponse2;
import com.example.health_platform.auth.dto.VerifyRequest;
import com.example.health_platform.auth.model.User;
import com.example.health_platform.auth.service.AuthService;

import jakarta.validation.Valid;
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
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest request) {
        String token = authService.login(request);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyAccount(@Valid @RequestBody VerifyRequest request) {
        String response = authService.verifyAccount(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        JwtResponse response = authService.refreshToken(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
public ResponseEntity<UserResponse2> getCurrentUser(HttpServletRequest request) {
    UserResponse2 userDto = authService.getCurrentUserDto(request); // <-- use DTO
    return ResponseEntity.ok(userDto);
}

}
