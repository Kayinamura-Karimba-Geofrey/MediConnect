package com.example.health_platform.modules.insurance.DTO;

import lombok.Data;

@Data
public class InsuranceResponseDTO {
    private Long id;
    private String providerName;
    private String insuranceNumber;
    private String coverageDetails;
    private boolean active;
    private Long userId;
}
