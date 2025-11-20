package com.example.health_platform.modules.admin.service;

import com.example.health_platform.auth.model.Role;
import com.example.health_platform.auth.model.User;
import com.example.health_platform.auth.repository.UserRepository;
import com.example.health_platform.modules.admin.DTO.AdminStatsDTO;
import com.example.health_platform.modules.appointment.model.Appointment;
import com.example.health_platform.modules.appointment.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final AppointmentRepository appointmentRepository;

    public AdminServiceImpl(UserRepository userRepository,
                            AppointmentRepository appointmentRepository) {
        this.userRepository = userRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User suspendUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setSuspended(true);  // You must add boolean suspended in User entity
        return userRepository.save(user);
    }

    @Override
    public AdminStatsDTO getPlatformStats() {
        long totalUsers = userRepository.count();
        long totalDoctors = userRepository.countByRole(Role.DOCTORS);
        long totalPatients = userRepository.countByRole(Role.PATIENTS);
        long totalAppointments = appointmentRepository.count();

        AdminStatsDTO dto = new AdminStatsDTO();
        dto.setTotalUsers(totalUsers);
        dto.setTotalDoctors(totalDoctors);
        dto.setTotalPatients(totalPatients);
        dto.setTotalAppointments(totalAppointments);

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

        if (doctor.getRole() != Role.DOCTORS) {
            throw new RuntimeException("User is not a doctor");
        }

        doctor.setApproved(true); 
        return userRepository.save(doctor);
    }
}
