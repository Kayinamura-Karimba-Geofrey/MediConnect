package com.example.health_platform.modules.appointment.controller;

import com.example.health_platform.auth.model.User;
import com.example.health_platform.auth.repository.UserRepository;
import com.example.health_platform.auth.service.AuthService;
import com.example.health_platform.modules.appointment.dto.AppointmentRequestDTO;
import com.example.health_platform.modules.appointment.model.Appointment;
import com.example.health_platform.modules.appointment.service.AppointmentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final AuthService authService;
    private final UserRepository userRepository;

    public AppointmentController(AppointmentService appointmentService, AuthService authService, UserRepository userRepository) {
        this.appointmentService = appointmentService;
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<Appointment> createAppointment(
            @RequestBody AppointmentRequestDTO dto,
            HttpServletRequest request
    ) {
        // Get the currently authenticated user (patient)
        User currentUser = authService.getCurrentUser(request);

        // Fetch the doctor entity using doctorId from DTO
        User doctor = userRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        // Create appointment
        Appointment appointment = appointmentService.createAppointment(currentUser, doctor, dto);

        return ResponseEntity.ok(appointment);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Appointment>> listAppointments(HttpServletRequest request) {
        User currentUser = authService.getCurrentUser(request);
        List<Appointment> appointments = appointmentService.getAppointmentsByPatient(currentUser);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointment(@PathVariable Long id) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        return ResponseEntity.ok(appointment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok("Appointment deleted successfully");
    }
}
