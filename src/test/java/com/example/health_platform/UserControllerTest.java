package com.example.health_platform;
import com.example.health_platform.modules.user.controller.UserController;
import org.springframework.web.multipart.MultipartFile;


import com.example.health_platform.auth.model.User;
import com.example.health_platform.modules.user.dto.UpdateProfileRequest;
import com.example.health_platform.modules.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    
    private User fakeUser() {
        User u = new User();
        u.setId(1L);
        u.setEmail("user@example.com");
        u.setFullName("Test User");
        return u;
    }

    @Test
    @DisplayName("PATCH /user/update-profile → updates profile")
    void updateProfile_shouldUpdateProfile() throws Exception {

        UpdateProfileRequest req = new UpdateProfileRequest();
        req.setFullName("New Name");
        req.setPhone("123456");

        User updated = fakeUser();
        updated.setFullName("New Name");

        Mockito.when(userService.updateProfile(any(UpdateProfileRequest.class), any()))
                .thenReturn(updated);

        mockMvc.perform(patch("/user/update-profile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("New Name"));
    }


    @Test
    @DisplayName("POST /user/{id}/upload-photo → uploads user photo")
    void uploadPhoto_shouldUpload() throws Exception {

        MockMultipartFile file =
                new MockMultipartFile("file", "avatar.jpg", "image/jpeg", "fakeimage".getBytes());

        User updated = fakeUser();
       updated.setProfilePhoto("fakeimage".getBytes());


        Mockito.when(userService.uploadProfilePhoto(eq(1L), any(MultipartFile.class)))
                .thenReturn(updated);

        mockMvc.perform(multipart("/user/1/upload-photo")
                        .file(file))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.profilePhoto").value("avatar.jpg"));
    }


    @Test
    @DisplayName("GET /user/{id} → returns user profile")
    void getUserById_shouldReturnUser() throws Exception {

        User user = fakeUser();

        Mockito.when(userService.getUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("user@example.com"))
                .andExpect(jsonPath("$.fullName").value("Test User"));
    }
}
