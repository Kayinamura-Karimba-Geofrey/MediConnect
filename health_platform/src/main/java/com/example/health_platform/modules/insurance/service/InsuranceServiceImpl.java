package com.example.health_platform.modules.insurance.service;

import com.example.health_platform.auth.model.User;
import com.example.health_platform.modules.insurance.DTO.CreateInsuranceDTO;
import com.example.health_platform.modules.insurance.DTO.InsuranceResponseDTO;
import com.example.health_platform.modules.insurance.model.Insurance;
import com.example.health_platform.modules.insurance.repository.InsuranceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InsuranceServiceImpl implements InsuranceService {

    private final InsuranceRepository insuranceRepository;

    public InsuranceServiceImpl(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }

    @Override
    public InsuranceResponseDTO addInsurance(User user, CreateInsuranceDTO dto) {
        Insurance insurance = Insurance.builder()
                .providerName(dto.getProviderName())
                .insuranceNumber(dto.getInsuranceNumber())
                .coverageDetails(dto.getCoverageDetails())
                .user(user)
                .build();

        Insurance saved = insuranceRepository.save(insurance);
        return mapToDTO(saved);
    }

    @Override
    public List<InsuranceResponseDTO> getMyInsurance(User user) {
        return insuranceRepository.findByUser(user)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InsuranceResponseDTO deactivateInsurance(Long id, User user) {
        Insurance insurance = insuranceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Insurance not found"));

        if (!insurance.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Not allowed");
        }

        insurance.setActive(false);
        Insurance saved = insuranceRepository.save(insurance);

        return mapToDTO(saved);
    }

    private InsuranceResponseDTO mapToDTO(Insurance i) {
        InsuranceResponseDTO dto = new InsuranceResponseDTO();
        dto.setId(i.getId());
        dto.setProviderName(i.getProviderName());
        dto.setInsuranceNumber(i.getInsuranceNumber());
        dto.setCoverageDetails(i.getCoverageDetails());
        dto.setActive(i.isActive());
        dto.setUserId(i.getUser().getId());
        return dto;
    }
}
