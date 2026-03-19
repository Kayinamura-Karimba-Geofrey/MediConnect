package com.example.health_platform.modules.billing.controller;

import com.example.health_platform.auth.model.User;
import com.example.health_platform.modules.user.service.UserService;
import com.example.health_platform.modules.billing.dto.BillDTO;
import com.example.health_platform.modules.billing.model.BillStatus;
import com.example.health_platform.modules.billing.service.BillingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/billing")
@RequiredArgsConstructor
public class BillingController {

    private final BillingService billingService;
    private final UserService userService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BillDTO> createBill(@RequestParam Long patientId, @RequestParam Double amount, @RequestParam String description) {
        User patient = userService.getUserById(patientId);
        return ResponseEntity.ok(billingService.createBill(patient, amount, description));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<BillDTO>> getBillsByPatient(@PathVariable Long patientId) {
        User patient = userService.getUserById(patientId);
        return ResponseEntity.ok(billingService.getBillsByPatient(patient));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<BillDTO>> getAllBills() {
        return ResponseEntity.ok(billingService.getAllBills());
    }

    @PatchMapping("/{billId}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BillDTO> updateBillStatus(@PathVariable Long billId, @RequestParam BillStatus status) {
        return ResponseEntity.ok(billingService.updateBillStatus(billId, status));
    }
}
