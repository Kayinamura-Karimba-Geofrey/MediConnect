package com.example.health_platform.modules.admin.DTO;

import lombok.Data;

@Data
public class PlatformStatsDTO {

    private long totalUsers;
    private long totalDoctors;
    private long totalPatients;
    private long totalAdmins;

    private long totalVisits;
    private long totalAppointments;
}
