package com.example.health_platform.auth.model.dto;

import com.example.health_platform.auth.model.Role;
import lombok.Data;

@Data
public class UserResponse2 {
    private Long id;
    private String fullName;
    private String email;
    private Role role;
    private String phone;
}

