package com.example.health_platform;

import com.example.health_platform.auth.model.User;
import com.example.health_platform.auth.repository.UserRepository;
import com.example.health_platform.auth.security.CustomUserDetailsService;
import com.example.health_platform.auth.security.JwtService;
import com.example.health_platform.auth.security.SecurityConfig;
import com.example.health_platform.modules.prescription.DTO.CreatePrescriptionDTO;
import com.example.health_platform.modules.prescription.DTO.PrescriptionResponseDTO;
import com.example.health_platform.modules.prescription.controller.PrescriptionController;
import com.example.health_platform.modules.prescription.service.PrescriptionService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;

@WebMvcTest(controllers = PrescriptionController.class)
@Import(SecurityConfig.class)
class PrescriptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PrescriptionService prescriptionService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private ObjectMapper objectMapper;

    
    @Test
    @DisplayName("POST /prescription/create -> create prescription")
    void createPrescription_shouldReturnPrescription() throws Exception {

        User doctor = new User();
        doctor.setId(1L);
        Authentication auth = new UsernamePasswordAuthenticationToken(
                doctor,
                null,
                List.of(new SimpleGrantedAuthority("ROLE_DOCTOR"))
        );

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

        mockMvc.perform(post("/prescription/create")
                .with(authentication(auth))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(100))
                .andExpect(jsonPath("$.doctorId").value(1))
                .andExpect(jsonPath("$.patientId").value(2))
                .andExpect(jsonPath("$.medicines[0]").value("Paracetamol 500mg"));
    }

    @Test
    @DisplayName("GET /prescription/{id} -> get prescription by ID")
    void getPrescriptionById_shouldReturnPrescription() throws Exception {
        User doctor = new User();
        doctor.setId(1L);
        Authentication auth = new UsernamePasswordAuthenticationToken(
                doctor,
                null,
                List.of(new SimpleGrantedAuthority("ROLE_DOCTOR"))
        );

        PrescriptionResponseDTO responseDTO = new PrescriptionResponseDTO();
        responseDTO.setId(101L);
        responseDTO.setDoctorId(1L);
        responseDTO.setPatientId(2L);
        responseDTO.setMedicines(List.of("Ibuprofen 200mg"));

        Mockito.when(prescriptionService.getPrescriptionById(101L))
               .thenReturn(responseDTO);

        mockMvc.perform(get("/prescription/101").with(authentication(auth)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(101))
                .andExpect(jsonPath("$.doctorId").value(1))
                .andExpect(jsonPath("$.patientId").value(2))
                .andExpect(jsonPath("$.medicines[0]").value("Ibuprofen 200mg"));
    }

    
    @Test
    @DisplayName("GET /prescription/patient/{patientId} -> get all prescriptions for patient")
    void getPrescriptionsByPatient_shouldReturnList() throws Exception {
        User doctor = new User();
        doctor.setId(1L);
        Authentication auth = new UsernamePasswordAuthenticationToken(
                doctor,
                null,
                List.of(new SimpleGrantedAuthority("ROLE_DOCTOR"))
        );

        PrescriptionResponseDTO responseDTO = new PrescriptionResponseDTO();
        responseDTO.setId(102L);
        responseDTO.setPatientId(2L);
         responseDTO.setMedicines(List.of("Amoxicillin 500mg"));

        Mockito.when(prescriptionService.getPrescriptionsByPatient(2L))
               .thenReturn(List.of(responseDTO));

        mockMvc.perform(get("/prescription/patient/2").with(authentication(auth)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(102))
                .andExpect(jsonPath("$[0].patientId").value(2))
                .andExpect(jsonPath("$[0].medicines[0]").value("Amoxicillin 500mg"));
    }

    
    @Test
    @DisplayName("PATCH /prescription/dispense/{id} -> mark as dispensed")
    void markAsDispensed_shouldReturnUpdatedPrescription() throws Exception {
        User doctor = new User();
        doctor.setId(1L);
        Authentication auth = new UsernamePasswordAuthenticationToken(
                doctor,
                null,
                List.of(new SimpleGrantedAuthority("ROLE_DOCTOR"))
        );

        PrescriptionResponseDTO responseDTO = new PrescriptionResponseDTO();
        responseDTO.setId(103L);
         responseDTO.setMedicines(List.of("Ibuprofen 200mg"));
        responseDTO.setDispensed(true);

        Mockito.when(prescriptionService.markAsDispensed(103L))
               .thenReturn(responseDTO);

        mockMvc.perform(patch("/prescription/dispense/103").with(authentication(auth)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(103))
                .andExpect(jsonPath("$.dispensed").value(true));
    }

    
    @Test
    @DisplayName("Unauthorized user -> forbidden access")
    void nonAuthorized_shouldBeForbidden() throws Exception {
        mockMvc.perform(get("/prescription/101"))
                .andExpect(status().isForbidden());
    }
}
