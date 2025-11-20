package com.example.health_platform.modules.Analytics.service;

import com.example.health_platform.modules.Analytics.DTO.PlatformStatsDTO;

import java.util.Map;
import java.util.List;

public interface AnalyticsService {

    PlatformStatsDTO getPlatformStats();

    Double getTotalRevenue();

    Map<String, Long> getDailyVisits();

    List<Map<String, Object>> getTopDoctors();
}
