package com.example.health_platform.modules.billing.service;

import com.example.health_platform.auth.model.User;
import com.example.health_platform.modules.billing.DTO.BillDTO;
import com.example.health_platform.modules.billing.model.Bill;
import com.example.health_platform.modules.billing.model.BillStatus;
import com.example.health_platform.modules.billing.repository.BillRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillingServiceImpl implements BillingService {

    private final BillRepository billRepository;

    public BillingServiceImpl(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public BillDTO createBill(User patient, Double amount, String description) {
        Bill bill = new Bill();
        bill.setPatient(patient);
        bill.setAmount(amount);
        bill.setDescription(description);

        Bill saved = billRepository.save(bill);
        return mapToDTO(saved);
    }

    @Override
    public List<BillDTO> getBillsByPatient(User patient) {
        return billRepository.findByPatient(patient)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BillDTO> getAllBills() {
        return billRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BillDTO updateBillStatus(Long billId, BillStatus status) {
        Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new RuntimeException("Bill not found"));
        bill.setStatus(status);
        Bill saved = billRepository.save(bill);
        return mapToDTO(saved);
    }

    private BillDTO mapToDTO(Bill bill) {
        BillDTO dto = new BillDTO();
        dto.setId(bill.getId());
        dto.setPatientId(bill.getPatient().getId());
        dto.setAmount(bill.getAmount());
        dto.setDescription(bill.getDescription());
        dto.setStatus(bill.getStatus());
        return dto;
    }
}
