package com.example.health_platform.modules.pharmarcy.DTO;

import java.util.List;

public class CreatePharmacyOrderDTO {

    private Long prescriptionId;
    private List<String> medicines; 

    public Long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public List<String> getMedicines() {   
        return medicines;
    }

    public void setMedicines(List<String> medicines) { 
        this.medicines = medicines;
    }
}
