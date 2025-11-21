package com.example.health_platform.auth.service;

import com.example.health_platform.auth.dto.JwtResponse;
import com.example.health_platform.auth.dto.LoginRequest;
import com.example.health_platform.auth.dto.RefreshTokenRequest;
import com.example.health_platform.auth.dto.RegisterRequest;
import com.example.health_platform.auth.dto.UserResponse2;
import com.example.health_platform.auth.dto.VerifyRequest;
import com.example.health_platform.auth.model.User;
import com.example.health_platform.auth.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(RegisterRequest request) {
        // Implement registration logic
        return null;
    }

    @Override
    public String login(LoginRequest request) {
        return null;
    }

    @Override
    public String verifyAccount(VerifyRequest request) {
        return null;
    }

    @Override
    public JwtResponse refreshToken(RefreshTokenRequest request) {
        return null;
    }

    // Returns actual User entity
    @Override
    public User getCurrentUser(HttpServletRequest request) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = (principal instanceof UserDetails) ?
                ((UserDetails) principal).getUsername() : principal.toString();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Returns UserResponse2 DTO for controller response
    @Override
    public UserResponse2 getCurrentUserDto(HttpServletRequest request) {
        User user = getCurrentUser(request);
        UserResponse2 dto = new UserResponse2();
        dto.setId(user.getId());
        dto.setFullName(user.getFullName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }
}
