package com.example.health_platform.modules.appointment.repository;

import com.example.health_platform.modules.appointment.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByPatientId(Long patientId);

    List<Appointment> findByDoctorId(Long doctorId);
    @Query("SELECT a.doctor.id, a.doctor.fullName, COUNT(a) " +
       "FROM Appointment a GROUP BY a.doctor.id, a.doctor.fullName ORDER BY COUNT(a) DESC")
List<Object[]> countAppointmentsPerDoctor();

}
