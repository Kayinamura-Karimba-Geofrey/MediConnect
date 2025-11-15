package com.example.health_platform.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.example.health_platform.auth.model.User;
import com.example.health_platform.auth.model.Role;
import com.example.health_platform.auth.model.dto.LoginRequest;
import com.example.health_platform.auth.model.dto.RegisterRequest;
import com.example.health_platform.auth.repository.UserRepository;
import com.example.health_platform.auth.security.JwtUtils;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Override
    public User register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }

        User newUser = new User();
        newUser.setFullName(request.getFullName());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setPhone(request.getPhone());
        if (request.getRole() != null && !request.getRole().isEmpty()) {
            newUser.setRole(Role.valueOf(request.getRole().toUpperCase()));
        } else {
            newUser.setRole(Role.PATIENTS); // default role
        }

        return userRepository.save(newUser);
    }
    @Override
    public String login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        
        return jwtUtils.generateToken(user.getEmail());
    }
}


