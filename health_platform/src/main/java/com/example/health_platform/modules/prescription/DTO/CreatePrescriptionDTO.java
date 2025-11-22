package com.example.health_platform.modules.prescription.DTO;

import java.util.List;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class CreatePrescriptionDTO {

    @NotNull
    private Long patientId;

    @NotEmpty
    private List<String> medicines;

    private String notes;
     
}
