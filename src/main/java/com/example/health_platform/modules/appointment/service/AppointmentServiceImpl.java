package com.example.health_platform.modules.appointment.service;

import com.example.health_platform.auth.model.User;
import com.example.health_platform.modules.appointment.dto.AppointmentRequestDTO;
import com.example.health_platform.modules.appointment.model.Appointment;
import com.example.health_platform.modules.appointment.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment createAppointment(User patient, User doctor, AppointmentRequestDTO dto) {
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setReason(dto.getReason());
        appointment.setStatus("PENDING");
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

    @Override
    public Appointment updateAppointment(Long id, LocalDateTime newDate) {
        Appointment appointment = getAppointmentById(id);
        appointment.setAppointmentDate(newDate);
        appointment.setStatus("PENDING"); // Reset status if needed
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment cancelAppointment(Long id, User patient) {
        Appointment appointment = getAppointmentById(id);
        if (!appointment.getPatient().getId().equals(patient.getId())) {
            throw new RuntimeException("You can only cancel your own appointments");
        }
        appointment.setStatus("CANCELLED");
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment approveAppointment(Long id, User doctor) {
        Appointment appointment = getAppointmentById(id);
        if (!appointment.getDoctor().getId().equals(doctor.getId())) {
            throw new RuntimeException("You can only approve your own appointments");
        }
        appointment.setStatus("APPROVED");
        return appointmentRepository.save(appointment);
    }
}
