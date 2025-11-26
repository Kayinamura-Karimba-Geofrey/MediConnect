package com.example.health_platform;

import com.example.health_platform.modules.admin.controller.AdminController;
import com.example.health_platform.auth.model.User;
import com.example.health_platform.modules.admin.DTO.AdminStatsDTO;
import com.example.health_platform.modules.admin.service.AdminService;

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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdminController.class)
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdminService adminService;

    // ---------- GET /admin/users ----------
    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("GET /admin/users -> returns list of users")
    void listUsers_shouldReturnUsers() throws Exception {
        User u = new User();
        u.setId(1L);
        u.setEmail("john@example.com");
        u.setActive(true); // Ensure active field exists

        Mockito.when(adminService.getAllUsers()).thenReturn(List.of(u));

        mockMvc.perform(get("/admin/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].email").value("john@example.com"))
                .andExpect(jsonPath("$[0].active").value(true));
    }

    // ---------- PATCH /admin/suspend/{id} ----------
    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("PATCH /admin/suspend/{id} -> suspends user")
    void suspendUser_shouldSuspend() throws Exception {

        User suspended = new User();
        suspended.setId(5L);
        suspended.setActive(false); // Ensure this setter exists

        Mockito.when(adminService.suspendUser(anyLong())).thenReturn(suspended);

        mockMvc.perform(patch("/admin/suspend/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.active").value(false));
    }

    // ---------- GET /admin/stats ----------
    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("GET /admin/stats -> returns admin statistics")
    void stats_shouldReturnStats() throws Exception {

        AdminStatsDTO stats = new AdminStatsDTO();
        stats.setTotalUsers(10);
        stats.setActiveDoctors(3); // Ensure setter exists

        Mockito.when(adminService.getPlatformStats()).thenReturn(stats);

        mockMvc.perform(get("/admin/stats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalUsers").value(10))
                .andExpect(jsonPath("$.activeDoctors").value(3));
    }

    // ---------- GET /admin/appointments ----------
    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("GET /admin/appointments -> returns appointments list")
    void getAppointments_shouldReturnList() throws Exception {

        Mockito.when(adminService.getAllAppointments()).thenReturn(List.of("Appointment1"));

        mockMvc.perform(get("/admin/appointments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("Appointment1"));
    }

    // ---------- PATCH /admin/doctor/approve/{id} ----------
    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("PATCH /admin/doctor/approve/{id} -> approves doctor")
    void approveDoctor_shouldApprove() throws Exception {

        User doctor = new User();
        doctor.setId(7L);
        doctor.setApproved(true); // Ensure this setter exists

        Mockito.when(adminService.approveDoctor(anyLong())).thenReturn(doctor);

        mockMvc.perform(patch("/admin/doctor/approve/7"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(7))
                .andExpect(jsonPath("$.approved").value(true));
    }

    // ---------- UNAUTHORIZED REQUEST TEST ----------
    @Test
    @DisplayName("Unauthorized user -> forbidden access to /admin endpoints")
    void nonAdmin_shouldBeForbidden() throws Exception {
        mockMvc.perform(get("/admin/users"))
                .andExpect(status().isForbidden());
    }
}
