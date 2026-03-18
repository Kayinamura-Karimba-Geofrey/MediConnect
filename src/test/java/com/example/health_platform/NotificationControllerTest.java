package com.example.health_platform;

import com.example.health_platform.auth.repository.UserRepository;
import com.example.health_platform.auth.security.CustomUserDetailsService;
import com.example.health_platform.auth.security.JwtService;
import com.example.health_platform.auth.security.SecurityConfig;
import com.example.health_platform.modules.notification.controller.NotificationController;
import com.example.health_platform.modules.notification.DTO.NotificationResponse;
import com.example.health_platform.modules.notification.service.NotificationService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NotificationController.class)
@Import(SecurityConfig.class)
@WithMockUser
class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationService notificationService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @Test
    @DisplayName("GET /notifications/{userId} -> returns notifications list")
    void getMyNotifications_shouldReturnList() throws Exception {
        NotificationResponse n1 = NotificationResponse.builder()
                .id(1L)
                .message("New appointment scheduled")
                .isRead(false)
                .timeStamp(LocalDateTime.now())
                .build();

        Mockito.when(notificationService.getMyNotifications(anyLong()))
                .thenReturn(List.of(n1));

        mockMvc.perform(get("/notifications/my").param("userId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].message").value("New appointment scheduled"))
                .andExpect(jsonPath("$[0].read").value(false));
    }

    @Test
    @DisplayName("PATCH /notifications/mark-as-read/{id} -> marks notification as read")
    void markAsRead_shouldReturnUpdatedNotification() throws Exception {
        NotificationResponse n = NotificationResponse.builder()
                .id(2L)
                .message("Test notification")
                .isRead(true)
                .timeStamp(LocalDateTime.now())
                .build();

        Mockito.when(notificationService.markAsRead(anyLong())).thenReturn(n);

        mockMvc.perform(patch("/notifications/read/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.message").value("Test notification"))
                .andExpect(jsonPath("$.read").value(true));
    }
}
