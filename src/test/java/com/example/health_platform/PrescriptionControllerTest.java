package com.example.health_platform;

import com.example.health_platform.auth.model.User;
import com.example.health_platform.modules.prescription.DTO.CreatePrescriptionDTO;
import com.example.health_platform.modules.prescription.DTO.PrescriptionResponseDTO;
import com.example.health_platform.modules.prescription.service.PrescriptionService;

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

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class PrescriptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PrescriptionService prescriptionService;

    @Autowired
    private ObjectMapper objectMapper;

    
    @Test
    @WithMockUser(roles = "DOCTOR")
    @DisplayName("POST /doctor/prescriptions -> create prescription")
    void createPrescription_shouldReturnPrescription() throws Exception {

        User doctor = new User();
        doctor.setId(1L);

        CreatePrescriptionDTO requestDTO = new CreatePrescriptionDTO();
        requestDTO.setPatientId(2L);
        requestDTO.setMedicines(List.of("Paracetamol 500mg"));

        PrescriptionResponseDTO responseDTO = new PrescriptionResponseDTO();
        responseDTO.setId(100L);
        responseDTO.setDoctorId(1L);
        responseDTO.setPatientId(2L);
        responseDTO.setMedicines(List.of("Paracetamol 500mg"));

        Mockito.when(prescriptionService.createPrescription(any(User.class), any(CreatePrescriptionDTO.class)))
               .thenReturn(responseDTO);

        mockMvc.perform(post("/doctor/prescriptions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(100))
                .andExpect(jsonPath("$.doctorId").value(1))
                .andExpect(jsonPath("$.patientId").value(2))
                .andExpect(jsonPath("$.medication").value("Paracetamol 500mg"));
    }

    @Test
    @WithMockUser(roles = "DOCTOR")
    @DisplayName("GET /doctor/prescriptions/{id} -> get prescription by ID")
    void getPrescriptionById_shouldReturnPrescription() throws Exception {

        PrescriptionResponseDTO responseDTO = new PrescriptionResponseDTO();
        responseDTO.setId(101L);
        responseDTO.setDoctorId(1L);
        responseDTO.setPatientId(2L);
        responseDTO.setMedicines(List.of("Ibuprofen 200mg"));

        Mockito.when(prescriptionService.getPrescriptionById(101L))
               .thenReturn(responseDTO);

        mockMvc.perform(get("/doctor/prescriptions/101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(101))
                .andExpect(jsonPath("$.doctorId").value(1))
                .andExpect(jsonPath("$.patientId").value(2))
                .andExpect(jsonPath("$.medication").value("Ibuprofen 200mg"));
    }

    
    @Test
    @WithMockUser(roles = "DOCTOR")
    @DisplayName("GET /doctor/prescriptions/patient/{patientId} -> get all prescriptions for patient")
    void getPrescriptionsByPatient_shouldReturnList() throws Exception {

        PrescriptionResponseDTO responseDTO = new PrescriptionResponseDTO();
        responseDTO.setId(102L);
        responseDTO.setPatientId(2L);
         responseDTO.setMedicines(List.of("Amoxicillin 500mg"));

        Mockito.when(prescriptionService.getPrescriptionsByPatient(2L))
               .thenReturn(List.of(responseDTO));

        mockMvc.perform(get("/doctor/prescriptions/patient/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(102))
                .andExpect(jsonPath("$[0].patientId").value(2))
                .andExpect(jsonPath("$[0].medication").value("Amoxicillin 500mg"));
    }

    
    @Test
    @WithMockUser(roles = "DOCTOR")
    @DisplayName("PATCH /doctor/prescriptions/dispense/{id} -> mark as dispensed")
    void markAsDispensed_shouldReturnUpdatedPrescription() throws Exception {

        PrescriptionResponseDTO responseDTO = new PrescriptionResponseDTO();
        responseDTO.setId(103L);
         responseDTO.setMedicines(List.of("Ibuprofen 200mg"));
        responseDTO.setDispensed(true);

        Mockito.when(prescriptionService.markAsDispensed(103L))
               .thenReturn(responseDTO);

        mockMvc.perform(patch("/doctor/prescriptions/dispense/103"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(103))
                .andExpect(jsonPath("$.dispensed").value(true));
    }

    
    @Test
    @DisplayName("Unauthorized user -> forbidden access")
    void nonAuthorized_shouldBeForbidden() throws Exception {
        mockMvc.perform(get("/doctor/prescriptions/101"))
                .andExpect(status().isForbidden());
    }
}
