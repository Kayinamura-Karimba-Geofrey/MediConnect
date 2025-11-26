package com.example.health_platform;

import com.example.health_platform.modules.notification.controller.NotificationController;
import com.example.health_platform.modules.notification.DTO.NotificationResponse;
import com.example.health_platform.modules.notification.service.NotificationService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NotificationController.class)
class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationService notificationService;

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

        mockMvc.perform(get("/notifications/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].message").value("New appointment scheduled"))
                .andExpect(jsonPath("$[0].isRead").value(false));
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

        mockMvc.perform(patch("/notifications/mark-as-read/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.message").value("Test notification"))
                .andExpect(jsonPath("$.isRead").value(true));
    }

    @Test
    @DisplayName("POST /notifications/create -> creates a notification")
    void createNotification_shouldReturnOk() throws Exception {
        
        Mockito.doNothing().when(notificationService).createNotification(anyLong(), anyString());

        mockMvc.perform(post("/notifications/create")
                .param("userId", "1")
                .param("message", "New test notification")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
