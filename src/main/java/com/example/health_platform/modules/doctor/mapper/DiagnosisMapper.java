package com.example.health_platform.modules.doctor.mapper;
import com.example.health_platform.modules.doctor.DTO.DiagnosisRequestDTO;


import com.example.health_platform.modules.doctor.model.Diagnosis;
import com.example.health_platform.modules.visit.model.Visit;
import com.example.health_platform.auth.model.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DiagnosisMapper {

    DiagnosisMapper INSTANCE = Mappers.getMapper(DiagnosisMapper.class);

    @Mapping(source = "doctor.id", target = "doctorId")
    @Mapping(source = "visit.id", target = "visitId")
    DiagnosisRequestDTO toDTO(Diagnosis diagnosis);

    @Mapping(source = "doctorId", target = "doctor")
    @Mapping(source = "visitId", target = "visit")
    Diagnosis toEntity(DiagnosisRequestDTO dto);

    
    default User mapDoctor(Long id) {
        if (id == null) return null;
        User u = new User();
        u.setId(id);
        return u;
    }

    
    default Visit mapVisit(Long id) {
        if (id == null) return null;
        Visit v = new Visit();
        v.setId(id);
        return v;
    }
}
