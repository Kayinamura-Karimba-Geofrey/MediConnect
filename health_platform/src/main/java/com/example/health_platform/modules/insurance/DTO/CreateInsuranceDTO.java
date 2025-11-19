package com.example.health_platform.modules.insurance.DTO;

import lombok.Data;

@Data
public class CreateInsuranceDTO {
    private String providerName;
    private String insuranceNumber;
    private String coverageDetails;
}
