package com.example.health_platform.modules.admin.DTO;

import lombok.Data;

@Data
public class AdminStatsDTO {
    private long totalUsers;
    private long totalDoctors;
    private long totalPatients;
    private long totalAppointments;
    private long approvedDoctors;
}
