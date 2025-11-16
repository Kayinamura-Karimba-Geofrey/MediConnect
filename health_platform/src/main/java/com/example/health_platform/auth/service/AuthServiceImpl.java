package com.example.health_platform.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.example.health_platform.auth.model.User;
import com.example.health_platform.auth.model.Role;
import com.example.health_platform.auth.model.dto.JwtResponse;
import com.example.health_platform.auth.model.dto.LoginRequest;
import com.example.health_platform.auth.model.dto.RefreshTokenRequest;
import com.example.health_platform.auth.model.dto.RegisterRequest;
import com.example.health_platform.auth.model.dto.UserResponse2;
import com.example.health_platform.auth.model.dto.VerifyRequest;
import com.example.health_platform.auth.repository.UserRepository;
import com.example.health_platform.auth.security.JwtService;

import jakarta.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

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

        // Ensure Role is type-safe
        if (request.getRole() != null && !request.getRole().isEmpty()) {
            newUser.setRole(Role.valueOf(request.getRole().toUpperCase()));
        } else {
            newUser.setRole(Role.PATIENTS);
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

        // Generate access token with user ID and Role
        return jwtService.generateAccessToken(user.getId().toString(), user.getRole());
    }

    @Override
    public String verifyAccount(VerifyRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.isVerified()) {
            return "Account already verified";
        }

        if (user.getVerificationCode() == null || !user.getVerificationCode().equals(request.getVerificationCode())) {
            throw new RuntimeException("Invalid verification code");
        }

        user.setVerified(true);
        userRepository.save(user);
        return "Account verified successfully";
    }

    @Override
    public JwtResponse refreshToken(RefreshTokenRequest request) {
        String refreshToken = request.getRefreshToken();
        try {
            String userId = jwtService.extractUserIdFromRefreshToken(refreshToken);
            User user = userRepository.findById(Long.parseLong(userId))
                    .orElseThrow(() -> new RuntimeException("User not found"));

            if (user.getRefreshToken() == null || !user.getRefreshToken().equals(refreshToken)) {
                throw new RuntimeException("Refresh token does not match");
            }

            // Generate new access token (type-safe Role)
            String newAccessToken = jwtService.generateAccessToken(user.getId().toString(), user.getRole());
            return new JwtResponse(newAccessToken);
        } catch (Exception e) {
            throw new RuntimeException("Invalid refresh token", e);
        }
    }

    @Override
    public UserResponse2 getCurrentUser(HttpServletRequest request) {
        User user = (User) request.getAttribute("authUser");

        if (user == null) {
            throw new RuntimeException("Unauthorized");
        }

        // Type-safe Role assignment
        UserResponse2 response = new UserResponse2();
        response.setId(user.getId());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole()); // Ensure UserResponse2.role is Role, not String
        response.setPhone(user.getPhone());

        return response;
    }
}
