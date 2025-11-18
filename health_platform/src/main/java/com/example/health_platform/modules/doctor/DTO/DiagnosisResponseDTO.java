package com.example.health_platform.modules.doctor.DTO;

public class DiagnosisResponseDTO {

    private Long id;
    private Long visitId;
    private String diagnosisText;
    private String treatment;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getVisitId() { return visitId; }
    public void setVisitId(Long visitId) { this.visitId = visitId; }

    public String getDiagnosisText() { return diagnosisText; }
    public void setDiagnosisText(String diagnosisText) { this.diagnosisText = diagnosisText; }

    public String getTreatment() { return treatment; }
    public void setTreatment(String treatment) { this.treatment = treatment; }
}
