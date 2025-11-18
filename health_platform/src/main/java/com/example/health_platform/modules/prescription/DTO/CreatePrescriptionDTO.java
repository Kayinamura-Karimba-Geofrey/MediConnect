package com.example.health_platform.modules.prescription.DTO;

import java.util.List;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CreatePrescriptionDTO {

    @NotNull
    private Long patientId;

    @NotEmpty
    private List<String> medicines;

    private String notes;

    
    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }

    public List<String> getMedicines() { return medicines; }
    public void setMedicines(List<String> medicines) { this.medicines = medicines; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
