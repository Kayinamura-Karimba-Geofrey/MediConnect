package com.example.health_platform.modules.prescription.model;

import com.example.health_platform.auth.model.User;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private User doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private User patient;

    @ElementCollection
    private List<String> medicines;

    private String notes;

    private boolean dispensed = false;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getDoctor() { return doctor; }
    public void setDoctor(User doctor) { this.doctor = doctor; }

    public User getPatient() { return patient; }
    public void setPatient(User patient) { this.patient = patient; }

    public List<String> getMedicines() { return medicines; }
    public void setMedicines(List<String> medicines) { this.medicines = medicines; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public boolean isDispensed() { return dispensed; }
    public void setDispensed(boolean dispensed) { this.dispensed = dispensed; }
}
