package com.example.health_platform.modules.appointment.dto;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class AppointmentRequestDTO {

    private Long doctorId; 
    private LocalDateTime appointmentDate;
    private String reason;

    
}
