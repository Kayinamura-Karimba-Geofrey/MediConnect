package com.example.health_platform.modules.appointment.service;

import com.example.health_platform.auth.model.User;
import com.example.health_platform.modules.appointment.dto.AppointmentRequestDTO;
import com.example.health_platform.modules.appointment.model.Appointment;
import java.time.LocalDateTime;


import java.util.List;

public interface AppointmentService {

    Appointment createAppointment(User patient, User doctor, AppointmentRequestDTO dto);

    List<Appointment> getAppointmentsByPatient(User patient);

    List<Appointment> getAppointmentsByDoctor(User doctor);

    Appointment getAppointmentById(Long id);

    void deleteAppointment(Long id);

    
    Appointment updateAppointment(Long id, LocalDateTime newDate);

    Appointment cancelAppointment(Long id, User patient);

    Appointment approveAppointment(Long id, User doctor);
}
