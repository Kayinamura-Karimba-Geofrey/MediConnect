package com.example.health_platform.modules.pharmarcy.DTO;

import java.util.List;
import lombok.Data;
@Data
public class CreatePharmacyOrderDTO {

    private Long prescriptionId;
    private List<String> medicines; 

    public List<String> getMedicines() {   
        return medicines;
    }

    public void setMedicines(List<String> medicines) { 
        this.medicines = medicines;
    }
}
