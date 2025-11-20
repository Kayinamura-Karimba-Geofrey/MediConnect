package com.example.health_platform.modules.Analytics.controller;
import com.example.health_platform.modules.Analytics.DTO.platformStatsDTO;
import com.example.health_platform.modules.Analytics.service.AnalyticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    // GET /analytics/stats
    @GetMapping("/stats")
    public ResponseEntity<PlatformStatsDTO> getStats() {
        return ResponseEntity.ok(analyticsService.getPlatformStats());
    }

    // GET /analytics/revenue
    @GetMapping("/revenue")
    public ResponseEntity<Double> getRevenue() {
        return ResponseEntity.ok(analyticsService.getTotalRevenue());
    }

    // GET /analytics/daily-visits
    @GetMapping("/daily-visits")
    public ResponseEntity<Map<String, Long>> getDailyVisits() {
        return ResponseEntity.ok(analyticsService.getDailyVisits());
    }

    // GET /analytics/top-doctors
    @GetMapping("/top-doctors")
    public ResponseEntity<List<Map<String, Object>>> getTopDoctors() {
        return ResponseEntity.ok(analyticsService.getTopDoctors());
    }
}
