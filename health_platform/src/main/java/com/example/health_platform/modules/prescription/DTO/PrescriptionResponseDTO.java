package com.example.health_platform.modules.prescription.DTO;

import java.util.List;

public class PrescriptionResponseDTO {

    private Long id;
    private Long doctorId;
    private Long patientId;
    private List<String> medicines;
    private String notes;
    private boolean dispensed;

    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getDoctorId() { return doctorId; }
    public void setDoctorId(Long doctorId) { this.doctorId = doctorId; }

    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }

    public List<String> getMedicines() { return medicines; }
    public void setMedicines(List<String> medicines) { this.medicines = medicines; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public boolean isDispensed() { return dispensed; }
    public void setDispensed(boolean dispensed) { this.dispensed = dispensed; }
}
