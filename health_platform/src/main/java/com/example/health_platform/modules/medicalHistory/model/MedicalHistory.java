package com.example.health_platform.modules.medicalHistory.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "medical_histories")
public class MedicalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String patientName;

    @Column(nullable = false)
    private LocalDate dateOfRecord;

    @Column(length = 1000)
    private String diagnosis;

    @Column(length = 2000)
    private String treatment;

    @Column(length = 2000)
    private String notes;

    // Constructors
    public MedicalHistory() {
    }

    public MedicalHistory(String patientName, LocalDate dateOfRecord, String diagnosis, String treatment, String notes) {
        this.patientName = patientName;
        this.dateOfRecord = dateOfRecord;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.notes = notes;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }

    public LocalDate getDateOfRecord() { return dateOfRecord; }
    public void setDateOfRecord(LocalDate dateOfRecord) { this.dateOfRecord = dateOfRecord; }

    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

    public String getTreatment() { return treatment; }
    public void setTreatment(String treatment) { this.treatment = treatment; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
