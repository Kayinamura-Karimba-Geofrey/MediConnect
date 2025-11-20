package com.example.health_platform.modules.Analytics.DTO;

public class PlatformStatsDTO {

    private long totalUsers;
    private long totalDoctors;
    private long totalPatients;
    private long totalAppointments;
    private long totalVisits;

    // Getters & Setters
    public long getTotalUsers() { return totalUsers; }
    public void setTotalUsers(long totalUsers) { this.totalUsers = totalUsers; }

    public long getTotalDoctors() { return totalDoctors; }
    public void setTotalDoctors(long totalDoctors) { this.totalDoctors = totalDoctors; }

    public long getTotalPatients() { return totalPatients; }
    public void setTotalPatients(long totalPatients) { this.totalPatients = totalPatients; }

    public long getTotalAppointments() { return totalAppointments; }
    public void setTotalAppointments(long totalAppointments) { this.totalAppointments = totalAppointments; }

    public long getTotalVisits() { return totalVisits; }
    public void setTotalVisits(long totalVisits) { this.totalVisits = totalVisits; }
}
