package com.example.health_platform.modules.admin.controller;

import com.example.health_platform.auth.model.User;
import com.example.health_platform.modules.admin.DTO.AdminStatsDTO;
import com.example.health_platform.modules.admin.service.AdminService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> listUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @PatchMapping("/suspend/{userId}")
    public ResponseEntity<User> suspendUser(@PathVariable Long userId) {
        return ResponseEntity.ok(adminService.suspendUser(userId));
    }

    @GetMapping("/stats")
    public ResponseEntity<AdminStatsDTO> getStats() {
        return ResponseEntity.ok(adminService.getPlatformStats());
    }

    @GetMapping("/appointments")
    public ResponseEntity<List<?>> getAllAppointments() {
        return ResponseEntity.ok(adminService.getAllAppointments());
    }

    @PatchMapping("/doctor/approve/{doctorId}")
    public ResponseEntity<User> approveDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok(adminService.approveDoctor(doctorId));
    }
}
