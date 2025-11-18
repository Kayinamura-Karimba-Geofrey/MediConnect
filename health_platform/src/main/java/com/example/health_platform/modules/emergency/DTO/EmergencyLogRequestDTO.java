package com.example.health_platform.modules.emergency.DTO;

public class EmergencyLogRequestDTO {

    private String token;
    private String accessedBy;
    private String status;

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getAccessedBy() { return accessedBy; }
    public void setAccessedBy(String accessedBy) { this.accessedBy = accessedBy; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

