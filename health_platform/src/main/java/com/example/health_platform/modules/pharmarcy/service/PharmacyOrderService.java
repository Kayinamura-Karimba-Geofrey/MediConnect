package com.example.health_platform.modules.pharmarcy.service;

import com.example.health_platform.auth.model.User;
import com.example.health_platform.modules.pharmarcy.DTO.CreatePharmacyOrderDTO;
import com.example.health_platform.modules.pharmarcy.DTO.PharmacyOrderResponseDTO;
import com.example.health_platform.modules.pharmarcy.model.OrderStatus;

import java.util.List;

public interface PharmacyOrderService {

    PharmacyOrderResponseDTO createOrder(User patient, CreatePharmacyOrderDTO dto);

    List<PharmacyOrderResponseDTO> getMyOrders(User patient);

    List<PharmacyOrderResponseDTO> getAllOrders();

    PharmacyOrderResponseDTO updateStatus(Long orderId, OrderStatus status); // <-- ADD THIS
}
