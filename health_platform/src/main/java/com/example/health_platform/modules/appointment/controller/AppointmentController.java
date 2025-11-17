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

import java.time.LocalDateTime;
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
    public ResponseEntity<Appointment> createAppointment(@RequestBody AppointmentRequestDTO dto, HttpServletRequest request) {
        User patient = authService.getCurrentUser(request);
        User doctor = userRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        Appointment appointment = appointmentService.createAppointment(patient, doctor, dto);
        return ResponseEntity.ok(appointment);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id,
                                                         @RequestParam LocalDateTime newDate) {
        Appointment updated = appointmentService.updateAppointment(id, newDate);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/cancel/{id}")
    public ResponseEntity<Appointment> cancelAppointment(@PathVariable Long id, HttpServletRequest request) {
        User patient = authService.getCurrentUser(request);
        Appointment cancelled = appointmentService.cancelAppointment(id, patient);
        return ResponseEntity.ok(cancelled);
    }

    @PatchMapping("/approve/{id}")
    public ResponseEntity<Appointment> approveAppointment(@PathVariable Long id, HttpServletRequest request) {
        User doctor = authService.getCurrentUser(request);
        Appointment approved = appointmentService.approveAppointment(id, doctor);
        return ResponseEntity.ok(approved);
    }

    @GetMapping("/my")
    public ResponseEntity<List<Appointment>> getMyAppointments(HttpServletRequest request) {
        User patient = authService.getCurrentUser(request);
        List<Appointment> appointments = appointmentService.getAppointmentsByPatient(patient);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/doctor/my")
    public ResponseEntity<List<Appointment>> getDoctorAppointments(HttpServletRequest request) {
        User doctor = authService.getCurrentUser(request);
        List<Appointment> appointments = appointmentService.getAppointmentsByDoctor(doctor);
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
