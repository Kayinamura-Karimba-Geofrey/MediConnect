package com.example.health_platform.modules.doctor.DTO;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DiagnosisRequestDTO {

    @NotNull
    private Long visitId;

    @NotBlank
    private String diagnosis;

    @NotBlank
    private String treatment;

    // getters & setters
    public Long getVisitId() { return visitId; }
    public void setVisitId(Long visitId) { this.visitId = visitId; }

    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

    public String getTreatment() { return treatment; }
    public void setTreatment(String treatment) { this.treatment = treatment; }
}
