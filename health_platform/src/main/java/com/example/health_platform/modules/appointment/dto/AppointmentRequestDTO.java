package com.example.health_platform.modules.appointment.dto;

import java.time.LocalDateTime;

public class AppointmentRequestDTO {

    private Long doctorId; // ID of the doctor
    private LocalDateTime appointmentDate;
    private String reason;

    // Getters and setters
    public Long getDoctorId() { return doctorId; }
    public void setDoctorId(Long doctorId) { this.doctorId = doctorId; }

    public LocalDateTime getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(LocalDateTime appointmentDate) { this.appointmentDate = appointmentDate; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}
