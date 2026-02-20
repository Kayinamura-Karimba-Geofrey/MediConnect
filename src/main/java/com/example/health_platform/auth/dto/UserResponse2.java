package com.example.health_platform.auth.dto;

import com.example.health_platform.auth.model.Role;
import lombok.Data;

@Data
public class UserResponse2 {
    private Long id;
    private String fullName;
    private String email;
    private String role;
    private String phone;

    
    public void setRole(Role role) {
        this.role = role != null ? role.name() : null;
    }
}


