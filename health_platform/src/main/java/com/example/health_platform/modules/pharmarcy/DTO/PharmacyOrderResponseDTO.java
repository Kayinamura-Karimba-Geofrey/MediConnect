package com.example.health_platform.modules.pharmarcy.DTO;

import lombok.Data;
import java.util.List;

@Data
public class PharmacyOrderResponseDTO {
    private Long id;
    private Long prescriptionId;
    private String status;
    private List<String> medicines; // <-- ADD THIS FIELD
}
