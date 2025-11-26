package com.example.health_platform;

import com.example.health_platform.auth.model.User;
import com.example.health_platform.modules.appointment.dto.AppointmentRequestDTO;
import com.example.health_platform.modules.appointment.model.Appointment;
import com.example.health_platform.modules.appointment.service.AppointmentService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppointmentService appointmentService;

    @Autowired
    private ObjectMapper objectMapper;

    
    @Test
    @WithMockUser(roles = "PATIENT")
    @DisplayName("POST /appointments -> create new appointment")
    void createAppointment_shouldReturnAppointment() throws Exception {

        User patient = new User();
        patient.setId(1L);
        User doctor = new User();
        doctor.setId(2L);

        AppointmentRequestDTO dto = new AppointmentRequestDTO();
        dto.setDate(LocalDateTime.of(2025, 11, 26, 14, 0));

        Appointment appointment = new Appointment();
        appointment.setId(100L);
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setDate(dto.getDate());

        Mockito.when(appointmentService.createAppointment(any(User.class), any(User.class), any(AppointmentRequestDTO.class)))
               .thenReturn(appointment);

        mockMvc.perform(post("/appointments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(100))
                .andExpect(jsonPath("$.patient.id").value(1))
                .andExpect(jsonPath("$.doctor.id").value(2));
    }

    
    @Test
    @WithMockUser(roles = "PATIENT")
    @DisplayName("GET /appointments/patient -> list patient appointments")
    void getAppointmentsByPatient_shouldReturnList() throws Exception {

        User patient = new User();
        patient.setId(1L);

        Appointment appt = new Appointment();
        appt.setId(101L);
        appt.setPatient(patient);

        Mockito.when(appointmentService.getAppointmentsByPatient(any(User.class)))
               .thenReturn(List.of(appt));

        mockMvc.perform(get("/appointments/patient"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(101))
                .andExpect(jsonPath("$[0].patient.id").value(1));
    }


    @Test
    @WithMockUser(roles = "DOCTOR")
    @DisplayName("GET /appointments/doctor -> list doctor appointments")
    void getAppointmentsByDoctor_shouldReturnList() throws Exception {

        User doctor = new User();
        doctor.setId(2L);

        Appointment appt = new Appointment();
        appt.setId(102L);
        appt.setDoctor(doctor);

        Mockito.when(appointmentService.getAppointmentsByDoctor(any(User.class)))
               .thenReturn(List.of(appt));

        mockMvc.perform(get("/appointments/doctor"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(102))
                .andExpect(jsonPath("$[0].doctor.id").value(2));
    }

    
    @Test
    @WithMockUser(roles = "PATIENT")
    @DisplayName("PATCH /appointments/{id} -> update appointment")
    void updateAppointment_shouldReturnUpdatedAppointment() throws Exception {

        LocalDateTime newDate = LocalDateTime.of(2025, 11, 27, 10, 0);

        Appointment appt = new Appointment();
        appt.setId(103L);
        appt.setDate(newDate);

        Mockito.when(appointmentService.updateAppointment(anyLong(), any(LocalDateTime.class)))
               .thenReturn(appt);

        mockMvc.perform(patch("/appointments/103")
                .param("date", newDate.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(103))
                .andExpect(jsonPath("$.date").value(newDate.toString()));
    }

    
    @Test
    @WithMockUser(roles = "PATIENT")
    @DisplayName("DELETE /appointments/{id} -> delete appointment")
    void deleteAppointment_shouldReturnOk() throws Exception {

        Mockito.doNothing().when(appointmentService).deleteAppointment(anyLong());

        mockMvc.perform(delete("/appointments/104"))
                .andExpect(status().isOk());
    }

    
    @Test
    @WithMockUser(roles = "PATIENT")
    @DisplayName("PATCH /appointments/cancel/{id} -> cancel appointment")
    void cancelAppointment_shouldReturnCancelled() throws Exception {

        User patient = new User();
        patient.setId(1L);

        Appointment appt = new Appointment();
        appt.setId(105L);
        appt.setPatient(patient);

        Mockito.when(appointmentService.cancelAppointment(anyLong(), any(User.class)))
               .thenReturn(appt);

        mockMvc.perform(patch("/appointments/cancel/105"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(105))
                .andExpect(jsonPath("$.patient.id").value(1));
    }

    
    @Test
    @WithMockUser(roles = "DOCTOR")
    @DisplayName("PATCH /appointments/approve/{id} -> approve appointment")
    void approveAppointment_shouldReturnApproved() throws Exception {

        User doctor = new User();
        doctor.setId(2L);

        Appointment appt = new Appointment();
        appt.setId(106L);
        appt.setDoctor(doctor);

        Mockito.when(appointmentService.approveAppointment(anyLong(), any(User.class)))
               .thenReturn(appt);

        mockMvc.perform(patch("/appointments/approve/106"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(106))
                .andExpect(jsonPath("$.doctor.id").value(2));
    }

    
    @Test
    @DisplayName("Unauthorized user -> forbidden access to /appointments endpoints")
    void nonAuthorized_shouldBeForbidden() throws Exception {
        mockMvc.perform(get("/appointments/patient"))
                .andExpect(status().isForbidden());
    }
}
