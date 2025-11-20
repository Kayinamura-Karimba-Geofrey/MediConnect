package com.example.health_platform.modules.billing.service;

import com.example.health_platform.auth.model.User;
import com.example.health_platform.modules.billing.DTO.BillDTO;
import com.example.health_platform.modules.billing.model.BillStatus;

import java.util.List;

public interface BillingService {

    BillDTO createBill(User patient, Double amount, String description);

    List<BillDTO> getBillsByPatient(User patient);

    List<BillDTO> getAllBills();

    BillDTO updateBillStatus(Long billId, BillStatus status);
}
