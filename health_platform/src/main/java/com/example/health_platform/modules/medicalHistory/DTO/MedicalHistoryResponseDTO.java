package com.example.health_platform.modules.medicalHistory.DTO;


import java.time.LocalDate;

public class MedicalHistoryResponseDTO {
    private Long id;
    private Long patientId;
    private LocalDate recordDate;
    private String diagnosis;
    private String treatment;
    private String notes;
    // getters/setters

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getPatientId() {
        return patientId;
    }
    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }
    public LocalDate getRecordDate() {
        return recordDate;
    }
    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }
    public String getDiagnosis() {
        return diagnosis;
    }
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
    public String getTreatment() {
        return treatment;
    }
    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

}
