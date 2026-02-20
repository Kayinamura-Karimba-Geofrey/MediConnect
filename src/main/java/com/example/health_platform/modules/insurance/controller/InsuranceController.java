package com.example.health_platform.modules.insurance.controller;

import com.example.health_platform.auth.model.User;
import com.example.health_platform.modules.insurance.DTO.CreateInsuranceDTO;
import com.example.health_platform.modules.insurance.service.InsuranceService;
import com.example.health_platform.modules.insurance.DTO.InsuranceResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/insurance")
public class InsuranceController {

    private final InsuranceService insuranceService;

    public InsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @PostMapping("/add")
    public InsuranceResponseDTO addInsurance(@RequestAttribute("user") User user,
                                             @RequestBody CreateInsuranceDTO dto) {
        return insuranceService.addInsurance(user, dto);
    }

    @GetMapping("/my")
    public List<InsuranceResponseDTO> getMyInsurance(@RequestAttribute("user") User user) {
        return insuranceService.getMyInsurance(user);
    }

    @PatchMapping("/deactivate/{id}")
    public InsuranceResponseDTO deactivate(@PathVariable Long id,
                                           @RequestAttribute("user") User user) {
        return insuranceService.deactivateInsurance(id, user);
    }
}
