package com.example.health_platform.modules.insurance.dto;

import lombok.Data;

@Data
public class CreateInsuranceDTO {
    private String providerName;
    private String insuranceNumber;
    private String coverageDetails;
}
