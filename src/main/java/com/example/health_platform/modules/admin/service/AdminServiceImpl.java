package com.example.health_platform.modules.admin.service;

import com.example.health_platform.auth.model.Role;
import com.example.health_platform.auth.model.User;
import com.example.health_platform.auth.repository.UserRepository;
import com.example.health_platform.modules.admin.DTO.AdminStatsDTO;
import com.example.health_platform.modules.appointment.model.Appointment;
import com.example.health_platform.modules.appointment.repository.AppointmentRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final AppointmentRepository appointmentRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User suspendUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setSuspended(true); // make sure User has this setter

        return userRepository.save(user);
    }

    @Override
    public AdminStatsDTO getPlatformStats() {
        long totalUsers = userRepository.count();
        long totalDoctors = userRepository.countByRole(Role.DOCTORS);
        long totalPatients = userRepository.countByRole(Role.PATIENTS);
        long totalAppointments = appointmentRepository.count();
        long approvedDoctors = userRepository.countApprovedDoctors();

        AdminStatsDTO dto = new AdminStatsDTO();
        dto.setTotalUsers(totalUsers);
        dto.setTotalDoctors(totalDoctors);
        dto.setTotalPatients(totalPatients);
        dto.setTotalAppointments(totalAppointments);
        dto.setApprovedDoctors(approvedDoctors);

        return dto;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public User approveDoctor(Long doctorId) {
        User doctor = userRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        doctor.setApproved(true);

        return userRepository.save(doctor);
    }
}
