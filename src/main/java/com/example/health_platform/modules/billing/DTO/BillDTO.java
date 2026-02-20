package com.example.health_platform.modules.billing.DTO;

import com.example.health_platform.modules.billing.model.BillStatus;

import lombok.Data;

@Data

public class BillDTO {

    private Long id;
    private Long patientId;
    private Double amount;
    private String description;
    private BillStatus status;

    
}
