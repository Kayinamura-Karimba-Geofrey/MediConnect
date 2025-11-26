package com.example.health_platform;

import com.example.health_platform.auth.dto.JwtResponse;
import com.example.health_platform.auth.dto.LoginRequest;
import com.example.health_platform.auth.dto.RefreshTokenRequest;
import com.example.health_platform.auth.dto.RegisterRequest;
import com.example.health_platform.auth.dto.UserResponse2;
import com.example.health_platform.auth.dto.VerifyRequest;
import com.example.health_platform.auth.model.User;
import com.example.health_platform.auth.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.example.health_platform.auth.controller.AuthController;
import com.example.health_platform.auth.model.Role;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("POST /auth/register -> returns created user JSON")
    void register_shouldReturnUser() throws Exception {
        RegisterRequest req = new RegisterRequest();
        req.setEmail("newuser@example.com");
        req.setPassword("password123");
        req.setFullName("New User");
        req.setRole("PATIENT");

        User returned = new User();
        returned.setId(1L);
        returned.setEmail(req.getEmail());
        returned.setFullName(req.getFullName());
        returned.setRole(null); 

        when(authService.register(any(RegisterRequest.class))).thenReturn(returned);

        mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("newuser@example.com"))
                .andExpect(jsonPath("$.fullName").value("New User"));

        verify(authService).register(ArgumentMatchers.any(RegisterRequest.class));
    }

    @Test
    @DisplayName("POST /auth/login -> returns jwt token JSON")
    void login_shouldReturnJwtToken() throws Exception {
        LoginRequest login = new LoginRequest();
        login.setEmail("user@example.com");
        login.setPassword("password");

        when(authService.login(any(LoginRequest.class))).thenReturn("mocked-jwt-token");

        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(login)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("mocked-jwt-token"));

        verify(authService).login(ArgumentMatchers.any(LoginRequest.class));
    }

    @Test
    @DisplayName("POST /auth/verify -> returns verification response string")
    void verify_shouldReturnString() throws Exception {
        VerifyRequest vr = new VerifyRequest();
        vr.setVerificationCode("123456");
        vr.setEmail("user@example.com");

        when(authService.verifyAccount(any(VerifyRequest.class))).thenReturn("verified");

        mockMvc.perform(post("/auth/verify")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vr)))
                .andExpect(status().isOk())
                .andExpect(content().string("verified"));

        verify(authService).verifyAccount(ArgumentMatchers.any(VerifyRequest.class));
    }

    @Test
    @DisplayName("POST /auth/refresh -> returns new jwt token wrapped in JwtResponse")
    void refresh_shouldReturnJwtResponse() throws Exception {
        RefreshTokenRequest req = new RefreshTokenRequest();
        req.setRefreshToken("some-refresh-token");

        JwtResponse resp = new JwtResponse("new-jwt-token");
        when(authService.refreshToken(any(RefreshTokenRequest.class))).thenReturn(resp);

        mockMvc.perform(post("/auth/refresh")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("new-jwt-token"));

        verify(authService).refreshToken(ArgumentMatchers.any(RefreshTokenRequest.class));
    }

    @Test
    @DisplayName("GET /auth/me -> returns current user DTO")
    void me_shouldReturnUserDto() throws Exception {
        UserResponse2 dto = new UserResponse2();
        dto.setId(10L);
        dto.setEmail("me@example.com");
        dto.setFullName("Me Myself");
        dto.setRole(Role.PATIENTS);

        
        when(authService.getCurrentUserDto(any(HttpServletRequest.class))).thenReturn(dto);

        mockMvc.perform(get("/auth/me")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.email").value("me@example.com"))
                .andExpect(jsonPath("$.fullName").value("Me Myself"));

        verify(authService).getCurrentUserDto(ArgumentMatchers.any(HttpServletRequest.class));
    }
}
