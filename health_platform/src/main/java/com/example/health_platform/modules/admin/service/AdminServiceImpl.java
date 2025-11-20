package com.example.health_platform.modules.admin.service;

import com.example.health_platform.auth.model.Role;
import com.example.health_platform.auth.model.User;
import com.example.health_platform.auth.repository.UserRepository;
import com.example.health_platform.modules.admin.DTO.AdminStatsDTO;
import com.example.health_platform.modules.appointment.repository.AppointmentRepository;
import com.example.health_platform.modules.doctor.repository.VisitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final VisitRepository visitRepository;
    private final AppointmentRepository appointmentRepository;

    public AdminServiceImpl(
            UserRepository userRepository,
            VisitRepository visitRepository,
            AppointmentRepository appointmentRepository
    ) {
        this.userRepository = userRepository;
        this.visitRepository = visitRepository;
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

        user.setSuspended(true);
        return userRepository.save(user);
    }

    @Override
    public AdminStatsDTO getPlatformStats() {
        AdminStatsDTO stats = new AdminStatsDTO();

        stats.setTotalUsers(userRepository.count());
        stats.setTotalVisits(visitRepository.count());
        stats.setTotalAppointments(appointmentRepository.count());

        return stats;
    }

    @Override
    public List<?> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public User approveDoctor(Long doctorId) {
        User doctor = userRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        if (doctor.getRole() != Role.DOCTORS) {
            throw new RuntimeException("User is not a doctor");
        }

        doctor.setDoctorApproved(true);
        return userRepository.save(doctor);
    }
}
