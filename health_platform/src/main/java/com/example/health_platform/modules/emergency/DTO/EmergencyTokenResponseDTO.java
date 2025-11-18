package com.example.health_platform.modules.emergency.DTO;



public class EmergencyTokenResponseDTO {

    private String token;
    private String expiresAt;

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getExpiresAt() { return expiresAt; }
    public void setExpiresAt(String expiresAt) { this.expiresAt = expiresAt; }
}
