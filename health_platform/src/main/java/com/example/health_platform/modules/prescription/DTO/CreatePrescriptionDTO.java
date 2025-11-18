package com.example.health_platform.modules.prescription.DTO;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreatePrescriptionDTO {

    @NotNull
    private Long patientId;

    @NotBlank
    private String medicines;

    private String notes;
}

