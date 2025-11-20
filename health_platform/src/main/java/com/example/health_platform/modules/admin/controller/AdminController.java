package com.example.health_platform.modules.admin.controller;

import com.example.health_platform.auth.model.User;
import com.example.health_platform.modules.admin.DTO.AdminStatsDTO;
import com.example.health_platform.modules.admin.service.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // GET /admin/users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }

    // PATCH /admin/suspend/:userId
    @PatchMapping("/suspend/{userId}")
    public User suspendUser(@PathVariable Long userId) {
        return adminService.suspendUser(userId);
    }

    // GET /admin/stats
    @GetMapping("/stats")
    public AdminStatsDTO getStats() {
        return adminService.getPlatformStats();
    }

    // GET /admin/appointments
    @GetMapping("/appointments")
    public List<?> getAllAppointments() {
        return adminService.getAllAppointments();
    }

    // PATCH /admin/doctor/approve/:id
    @PatchMapping("/doctor/approve/{id}")
    public User approveDoctorLicense(@PathVariable Long id) {
        return adminService.approveDoctor(id);
    }
}
