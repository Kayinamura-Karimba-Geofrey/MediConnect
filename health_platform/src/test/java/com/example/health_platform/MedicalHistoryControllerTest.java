package com.example.health_platform;

import com.example.health_platform.modules.medicalHistory.model.MedicalHistory;
import com.example.health_platform.modules.medicalHistory.service.MedicalHistoryService;
import com.example.health_platform.modules.medicalHistory.controller.MedicalHistoryController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MedicalHistoryController.class)
class MedicalHistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicalHistoryService service;

    @Test
    @DisplayName("POST /medical-history → should save a record")
    void createMedicalHistory() throws Exception {

        MedicalHistory history = new MedicalHistory();
        history.setId(1L);
        history.setPatientName("John Doe");
        history.setDateOfRecord(LocalDate.now());
        history.setDiagnosis("Flu");
        history.setTreatment("Rest");
        history.setNotes("N/A");

        Mockito.when(service.save(any(MedicalHistory.class))).thenReturn(history);

        mockMvc.perform(post("/medical-history")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "patientName": "John Doe",
                                "dateOfRecord": "2024-01-01",
                                "diagnosis": "Flu",
                                "treatment": "Rest",
                                "notes": "N/A"
                            }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.patientName").value("John Doe"))
                .andExpect(jsonPath("$.diagnosis").value("Flu"));
    }

    @Test
    @DisplayName("GET /medical-history → should return all records")
    void getAllMedicalHistory() throws Exception {

        MedicalHistory h1 = new MedicalHistory(1L, "John Doe",
                LocalDate.now(), "Cold", "Medication", "N/A");

        MedicalHistory h2 = new MedicalHistory(2L, "Jane Doe",
                LocalDate.now(), "Allergy", "Antihistamines", "N/A");

        Mockito.when(service.getAll()).thenReturn(List.of(h1, h2));

        mockMvc.perform(get("/medical-history"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].patientName").value("John Doe"))
                .andExpect(jsonPath("$[1].patientName").value("Jane Doe"));
    }

    @Test
    @DisplayName("GET /medical-history/{id} → should return record")
    void getMedicalHistoryById() throws Exception {

        MedicalHistory history = new MedicalHistory(
                1L, "John Doe",
                LocalDate.now(),
                "Fever", "Medication", "N/A"
        );

        Mockito.when(service.getById(1L)).thenReturn(Optional.of(history));

        mockMvc.perform(get("/medical-history/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patientName").value("John Doe"));
    }

    @Test
    @DisplayName("GET /medical-history/{id} → should return 404 if not found")
    void getMedicalHistoryById_NotFound() throws Exception {

        Mockito.when(service.getById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/medical-history/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /medical-history/{id} → should delete record")
    void deleteMedicalHistory() throws Exception {

        mockMvc.perform(delete("/medical-history/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("GET /medical-history/patient/{name} → return records for patient")
    void getByPatientName() throws Exception {

        MedicalHistory record = new MedicalHistory(
                1L, "John Doe",
                LocalDate.now(),
                "Infection", "Antibiotics", "N/A"
        );

        Mockito.when(service.getByPatientName("John Doe"))
                .thenReturn(List.of(record));

        mockMvc.perform(get("/medical-history/patient/John Doe"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].patientName").value("John Doe"))
                .andExpect(jsonPath("$[0].diagnosis").value("Infection"));
    }
}
