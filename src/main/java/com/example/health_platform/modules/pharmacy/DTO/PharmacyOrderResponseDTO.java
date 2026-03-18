package com.example.health_platform.modules.pharmacy.dto;

import lombok.Data;
import java.util.List;

@Data
public class PharmacyOrderResponseDTO {
    private Long id;
    private Long prescriptionId;
    private String status;
    private List<String> medicines; 
}
