package com.example.health_platform.modules.admin.service;

import com.example.health_platform.auth.model.User;
import com.example.health_platform.modules.admin.DTO.AdminStatsDTO;

import java.util.List;

public interface AdminService {

    List<User> getAllUsers();

    User suspendUser(Long userId);

    AdminStatsDTO getPlatformStats();

    List<?> getAllAppointments();

    User approveDoctor(Long doctorId);

    
}
