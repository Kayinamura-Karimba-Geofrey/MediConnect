package com.example.health_platform;

import com.example.health_platform.modules.doctor.DTO.DiagnosisRequestDTO;
import com.example.health_platform.modules.doctor.DTO.DiagnosisResponseDTO;
import com.example.health_platform.modules.doctor.service.DiagnosisService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class DiagnosisControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DiagnosisService diagnosisService;

    @Autowired
    private ObjectMapper objectMapper;

    // ---------- POST /doctor/diagnosis ----------
    @Test
    @WithMockUser(roles = "DOCTOR")
    @DisplayName("POST /doctor/diagnosis -> create a new diagnosis")
    void createDiagnosis_shouldReturnDiagnosis() throws Exception {

        DiagnosisRequestDTO requestDTO = new DiagnosisRequestDTO();
        requestDTO.setVisitId(1L);
        requestDTO.setNotes("Patient has fever");

        DiagnosisResponseDTO responseDTO = new DiagnosisResponseDTO();
        responseDTO.setId(100L);
        responseDTO.setVisitId(1L);
        responseDTO.setNotes("Patient has fever");

        Mockito.when(diagnosisService.createDiagnosis(any(DiagnosisRequestDTO.class)))
               .thenReturn(responseDTO);

        mockMvc.perform(post("/doctor/diagnosis")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(100))
                .andExpect(jsonPath("$.visitId").value(1))
                .andExpect(jsonPath("$.notes").value("Patient has fever"));
    }

    // ---------- GET /doctor/diagnosis/{visitId} ----------
    @Test
    @WithMockUser(roles = "DOCTOR")
    @DisplayName("GET /doctor/diagnosis/{visitId} -> returns diagnosis list")
    void getDiagnosisByVisit_shouldReturnList() throws Exception {

        DiagnosisResponseDTO responseDTO = new DiagnosisResponseDTO();
        responseDTO.setId(100L);
        responseDTO.setVisitId(1L);
        responseDTO.setNotes("Patient has fever");

        Mockito.when(diagnosisService.getDiagnosisByVisit(anyLong()))
               .thenReturn(List.of(responseDTO));

        mockMvc.perform(get("/doctor/diagnosis/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(100))
                .andExpect(jsonPath("$[0].visitId").value(1))
                .andExpect(jsonPath("$[0].notes").value("Patient has fever"));
    }

    // ---------- Unauthorized access ----------
    @Test
    @DisplayName("Unauthorized user -> forbidden access to /doctor endpoints")
    void nonDoctor_shouldBeForbidden() throws Exception {
        mockMvc.perform(get("/doctor/diagnosis/1"))
                .andExpect(status().isForbidden());
    }
}
