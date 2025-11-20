package com.example.health_platform.modules.admin.DTO;



import lombok.Data;

@Data
public class AdminStatsDTO {
    private long totalUsers;
    private long totalVisits;
    private long totalAppointments;

    
    private long totalDoctors;
    private long totalPatients;
    private long totalAdmins;
    private boolean approved =false;
    

    // getters
    public long getTotalUsers() { return totalUsers; }
    public long getTotalDoctors() { return totalDoctors; }
    public long getTotalPatients() { return totalPatients; }
    public long getTotalAdmins() { return totalAdmins; }
    public long getTotalVisits() { return totalVisits; }
    public long getTotalAppointments() { return totalAppointments; }
    public boolean isApproved() { return approved; }

    // setters
    public void setTotalUsers(long totalUsers) { this.totalUsers = totalUsers; }
    public void setTotalDoctors(long totalDoctors) { this.totalDoctors = totalDoctors; }
    public void setTotalPatients(long totalPatients) { this.totalPatients = totalPatients; }
    public void setTotalAdmins(long totalAdmins) { this.totalAdmins = totalAdmins; }
    public void setTotalVisits(long totalVisits) { this.totalVisits = totalVisits; }
    public void setTotalAppointments(long totalAppointments) { this.totalAppointments = totalAppointments; }
    public void setApproved(boolean approved) { this.approved = approved; }
}
