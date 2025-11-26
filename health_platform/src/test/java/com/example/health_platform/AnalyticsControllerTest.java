package com.example.health_platform;

import com.example.health_platform.modules.Analytics.controller.AnalyticsController;
import com.example.health_platform.modules.Analytics.DTO.PlatformStatsDTO;
import com.example.health_platform.modules.Analytics.service.AnalyticsService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;

// import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AnalyticsController.class)
class AnalyticsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnalyticsService analyticsService;

    @Test
    @DisplayName("GET /analytics/stats -> returns platform stats")
    void getPlatformStats_shouldReturnStats() throws Exception {
        PlatformStatsDTO dto = new PlatformStatsDTO();
        dto.setTotalUsers(100L);
        dto.setTotalDoctors(20L);
        dto.setTotalPatients(80L);
        dto.setTotalAppointments(150L);

        Mockito.when(analyticsService.getPlatformStats()).thenReturn(dto);

        mockMvc.perform(get("/analytics/stats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalUsers").value(100))
                .andExpect(jsonPath("$.totalDoctors").value(20))
                .andExpect(jsonPath("$.totalPatients").value(80))
                .andExpect(jsonPath("$.totalAppointments").value(150));
    }

    @Test
    @DisplayName("GET /analytics/revenue -> returns total revenue")
    void getTotalRevenue_shouldReturnRevenue() throws Exception {
        Mockito.when(analyticsService.getTotalRevenue()).thenReturn(1250.50);

        mockMvc.perform(get("/analytics/revenue"))
                .andExpect(status().isOk())
                .andExpect(content().string("1250.5"));
    }

    @Test
    @DisplayName("GET /analytics/daily-visits -> returns daily visits map")
    void getDailyVisits_shouldReturnMap() throws Exception {
        Map<String, Long> visits = Map.of(
                "2025-11-25", 20L,
                "2025-11-26", 15L
        );

        Mockito.when(analyticsService.getDailyVisits()).thenReturn(visits);

        mockMvc.perform(get("/analytics/daily-visits"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.['2025-11-25']").value(20))
                .andExpect(jsonPath("$.['2025-11-26']").value(15));
    }

    @Test
    @DisplayName("GET /analytics/top-doctors -> returns top doctors list")
    void getTopDoctors_shouldReturnList() throws Exception {
        List<Map<String, Object>> topDoctors = List.of(
                Map.of("doctorId", 1, "name", "Dr. Smith", "appointments", 25),
                Map.of("doctorId", 2, "name", "Dr. Jones", "appointments", 20)
        );

        Mockito.when(analyticsService.getTopDoctors()).thenReturn(topDoctors);

        mockMvc.perform(get("/analytics/top-doctors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].doctorId").value(1))
                .andExpect(jsonPath("$[0].name").value("Dr. Smith"))
                .andExpect(jsonPath("$[0].appointments").value(25))
                .andExpect(jsonPath("$[1].doctorId").value(2))
                .andExpect(jsonPath("$[1].name").value("Dr. Jones"))
                .andExpect(jsonPath("$[1].appointments").value(20));
    }
}
