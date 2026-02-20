package com.example.health_platform.modules.Analytics.controller;
import com.example.health_platform.modules.Analytics.service.AnalyticsService;
import com.example.health_platform.modules.admin.DTO.AdminStatsDTO;

import com.example.health_platform.modules.admin.service.AdminService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;
    private final AdminService adminService;

    public AnalyticsController(AnalyticsService analyticsService, AdminService adminService) {
        this.analyticsService = analyticsService;
        this.adminService = adminService;
    }

    
    @GetMapping("/stats")
    public ResponseEntity<AdminStatsDTO> getStats() {
        return ResponseEntity.ok(adminService.getPlatformStats());
    }

    
    @GetMapping("/revenue")
    public ResponseEntity<Double> getRevenue() {
        return ResponseEntity.ok(analyticsService.getTotalRevenue());
    }

    
    @GetMapping("/daily-visits")
    public ResponseEntity<Map<String, Long>> getDailyVisits() {
        return ResponseEntity.ok(analyticsService.getDailyVisits());
    }

    
    @GetMapping("/top-doctors")
    public ResponseEntity<List<Map<String, Object>>> getTopDoctors() {
        return ResponseEntity.ok(analyticsService.getTopDoctors());
    }
}
