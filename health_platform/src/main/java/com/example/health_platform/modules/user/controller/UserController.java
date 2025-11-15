package com.example.health_platform.modules.user.controller;

import com.example.health_platform.auth.model.User;
import com.example.health_platform.modules.user.dto.UpdateProfileRequest;
import com.example.health_platform.modules.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PatchMapping("/update-profile")
    public ResponseEntity<User> updateProfile(
            @RequestBody UpdateProfileRequest request,
            HttpServletRequest httpRequest) {
        User updatedUser = userService.updateProfile(request, httpRequest);
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping("/{id}/upload-photo")
    public ResponseEntity<User> uploadPhoto(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) throws IOException {
        User updatedUser = userService.uploadProfilePhoto(id, file);
        return ResponseEntity.ok(updatedUser);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserProfile(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
}
