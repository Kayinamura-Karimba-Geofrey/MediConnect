package com.example.health_platform.modules.medicalHistory.DTO;
import com.example.health_platform.modules.medicalHistory.model.MedicalHistory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface MedicalHistoryMapper {
    MedicalHistoryMapper INSTANCE = Mappers.getMapper(MedicalHistoryMapper.class);

    MedicalHistory toEntity(MedicalHistoryCreateDTO dto);

    MedicalHistoryResponseDTO toDTO(MedicalHistory entity);
}
