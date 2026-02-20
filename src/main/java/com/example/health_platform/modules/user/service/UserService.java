package com.example.health_platform.modules.user.service;

import com.example.health_platform.auth.model.User;
import com.example.health_platform.modules.user.dto.UpdateProfileRequest;
import com.example.health_platform.modules.user.repository.UserRepository2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository2 userRepository;

    
    public User updateProfile(UpdateProfileRequest request, HttpServletRequest httpRequest) {
        User authUser = (User) httpRequest.getAttribute("authUser");
        if (authUser == null) throw new RuntimeException("Unauthorized");

        if (request.getFullName() != null) authUser.setFullName(request.getFullName());
        if (request.getPhone() != null) authUser.setPhone(request.getPhone());
        if (request.getAddress() != null) authUser.setAddress(request.getAddress());

        return userRepository.save(authUser);
    }

    
    public User uploadProfilePhoto(Long id, MultipartFile file) throws IOException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setProfilePhoto(file.getBytes());
        return userRepository.save(user);
    }

    
    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }
}
