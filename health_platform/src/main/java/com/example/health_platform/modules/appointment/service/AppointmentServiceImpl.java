package com.example.health_platform.modules.appointment.service;

import com.example.health_platform.auth.model.User;
import com.example.health_platform.modules.appointment.dto.AppointmentRequestDTO;
import com.example.health_platform.modules.appointment.model.Appointment;
import com.example.health_platform.modules.appointment.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment createAppointment(User patient, User doctor, AppointmentRequestDTO dto) {
        // Create new appointment
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);  // now works because Appointment has doctor field
        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setReason(dto.getReason());
        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAppointmentsByPatient(User patient) {
        return appointmentRepository.findByPatientId(patient.getId());
    }

    @Override
    public List<Appointment> getAppointmentsByDoctor(User doctor) {
        return appointmentRepository.findByDoctorId(doctor.getId());
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}
