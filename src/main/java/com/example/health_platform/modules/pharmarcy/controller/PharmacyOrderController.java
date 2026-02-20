package com.example.health_platform.modules.pharmarcy.controller;

import com.example.health_platform.auth.model.User;
import com.example.health_platform.auth.service.AuthService;
import com.example.health_platform.modules.pharmarcy.DTO.CreatePharmacyOrderDTO;
import com.example.health_platform.modules.pharmarcy.DTO.PharmacyOrderResponseDTO;
import com.example.health_platform.modules.pharmarcy.model.OrderStatus;
import com.example.health_platform.modules.pharmarcy.service.PharmacyOrderService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pharmacy/order")
public class PharmacyOrderController {

    private final PharmacyOrderService orderService;
    private final AuthService authService;

    public PharmacyOrderController(PharmacyOrderService orderService, AuthService authService) {
        this.orderService = orderService;
        this.authService = authService;
    }

    @PostMapping("/from-prescription")
    public ResponseEntity<PharmacyOrderResponseDTO> createOrder(
            @RequestBody CreatePharmacyOrderDTO dto,
            HttpServletRequest request
    ) {
        User patient = authService.getCurrentUser(request);
        return ResponseEntity.ok(orderService.createOrder(patient, dto));
    }

    @GetMapping("/my")
    public ResponseEntity<List<PharmacyOrderResponseDTO>> getMyOrders(HttpServletRequest request) {
        User patient = authService.getCurrentUser(request);
        return ResponseEntity.ok(orderService.getMyOrders(patient));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PharmacyOrderResponseDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PatchMapping("/status/{orderId}")
    public ResponseEntity<PharmacyOrderResponseDTO> updateStatus(
            @PathVariable Long orderId,
            @RequestParam OrderStatus status
    ) {
        return ResponseEntity.ok(orderService.updateStatus(orderId, status));
    }
}
