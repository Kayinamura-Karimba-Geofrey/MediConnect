package com.example.health_platform.modules.medicalhistory.dto;
import com.example.health_platform.modules.medicalhistory.model.MedicalHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface MedicalHistoryMapper {
    MedicalHistoryMapper INSTANCE = Mappers.getMapper(MedicalHistoryMapper.class);

    @Mapping(source = "recordDate", target = "dateOfRecord")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "patientName", ignore = true)
    MedicalHistory toEntity(MedicalHistoryCreateDTO dto);
 
    @Mapping(source = "dateOfRecord", target = "recordDate")
    @Mapping(target = "patientId", ignore = true)
    MedicalHistoryResponseDTO toDTO(MedicalHistory entity);
}
