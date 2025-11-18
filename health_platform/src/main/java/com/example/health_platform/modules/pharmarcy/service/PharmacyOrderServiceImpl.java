package com.example.health_platform.modules.pharmarcy.service;


import com.example.health_platform.modules.pharmarcy.DTO.CreatePharmacyOrderDTO;
import com.example.health_platform.modules.pharmarcy.DTO.PharmacyOrderResponseDTO;  
import com.example.health_platform.modules.pharmarcy.model.OrderStatus;
import com.example.health_platform.modules.pharmarcy.model.PharmacyOrder;
import com.example.health_platform.modules.pharmarcy.repository.PharmacyOrderRepository;
import com.example.health_platform.modules.pharmarcy.repository.PrescriptionRepository;
import com.example.health_platform.auth.model.User;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PharmacyOrderServiceImpl implements PharmacyOrderService {

    private final PharmacyOrderRepository orderRepository;
    private final PrescriptionRepository prescriptionRepository;

    public PharmacyOrderServiceImpl(
            PharmacyOrderRepository orderRepository,
            PrescriptionRepository prescriptionRepository
    ) {
        this.orderRepository = orderRepository;
        this.prescriptionRepository = prescriptionRepository;
    }

    @Override
    public PharmacyOrderResponseDTO createOrder(User patient, CreatePharmacyOrderDTO dto) {

        Prescription prescription = prescriptionRepository.findById(dto.getPrescriptionId())
                .orElseThrow(() -> new RuntimeException("Prescription not found"));

        PharmacyOrder order = new PharmacyOrder();
        order.setPatient(patient);
        order.setPrescription(prescription);

        PharmacyOrder saved = orderRepository.save(order);

        return mapToDTO(saved);
    }

    @Override
    public List<PharmacyOrderResponseDTO> getMyOrders(User patient) {
        return orderRepository.findByPatient(patient)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PharmacyOrderResponseDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PharmacyOrderResponseDTO updateStatus(Long orderId, OrderStatus status) {

        PharmacyOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);

        PharmacyOrder saved = orderRepository.save(order);

        return mapToDTO(saved);
    }

    private PharmacyOrderResponseDTO mapToDTO(PharmacyOrder order) {
        PharmacyOrderResponseDTO res = new PharmacyOrderResponseDTO();
        res.setId(order.getId());
        res.setPrescriptionId(order.getPrescription().getId());
        res.setPatientName(order.getPatient().getFullName());
        res.setStatus(order.getStatus());
        return res;
    }
}
