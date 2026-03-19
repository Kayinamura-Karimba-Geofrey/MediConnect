package com.example.health_platform.modules.insurance.service;

import com.example.health_platform.auth.model.User;
import com.example.health_platform.modules.insurance.dto.CreateInsuranceDTO;
import com.example.health_platform.modules.insurance.dto.InsuranceResponseDTO;

import java.util.List;

public interface InsuranceService {

    InsuranceResponseDTO addInsurance(User user, CreateInsuranceDTO dto);

    List<InsuranceResponseDTO> getMyInsurance(User user);

    InsuranceResponseDTO deactivateInsurance(Long id, User user);
}
