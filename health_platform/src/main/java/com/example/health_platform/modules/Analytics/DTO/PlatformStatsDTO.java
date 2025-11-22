package com.example.health_platform.modules.Analytics.DTO;

import lombok.Data;

@Data

public class PlatformStatsDTO {

    private long totalUsers;
    private long totalDoctors;
    private long totalPatients;
    private long totalAppointments;
    private long totalVisits;

    
}
