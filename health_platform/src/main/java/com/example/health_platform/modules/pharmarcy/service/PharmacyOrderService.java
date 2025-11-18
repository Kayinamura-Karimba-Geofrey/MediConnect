package com.example.health_platform.modules.pharmarcy.service;

import com.example.health_platform.modules.pharmarcy.DTO.CreatePharmacyOrderDTO;
import com.example.health_platform.modules.pharmarcy.DTO.PharmacyOrderResponseDTO;
import com.example.health_platform.modules.pharmarcy.model.OrderStatus;
import java.util.List;
public interface PharmacyOrderService {

    PharmacyOrderResponseDTO createOrderFromPrescription(CreatePharmacyOrderDTO dto, Long patientId);

    List<PharmacyOrderResponseDTO> getMyOrders(Long patientId);

    List<PharmacyOrderResponseDTO> getAllOrders();

    PharmacyOrderResponseDTO updateOrderStatus(Long orderId, OrderStatus status);
}
