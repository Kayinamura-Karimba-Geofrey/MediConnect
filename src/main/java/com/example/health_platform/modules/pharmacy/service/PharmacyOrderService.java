package com.example.health_platform.modules.pharmacy.service;

import com.example.health_platform.auth.model.User;
import com.example.health_platform.modules.pharmacy.dto.CreatePharmacyOrderDTO;
import com.example.health_platform.modules.pharmacy.dto.PharmacyOrderResponseDTO;
import com.example.health_platform.modules.pharmacy.model.OrderStatus;

import java.util.List;

public interface PharmacyOrderService {

    PharmacyOrderResponseDTO createOrder(User patient, CreatePharmacyOrderDTO dto);

    List<PharmacyOrderResponseDTO> getMyOrders(User patient);

    List<PharmacyOrderResponseDTO> getAllOrders();

    PharmacyOrderResponseDTO updateStatus(Long orderId, OrderStatus status); 
}
