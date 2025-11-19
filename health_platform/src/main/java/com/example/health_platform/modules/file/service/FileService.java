package com.example.health_platform.modules.file.service;

import com.example.health_platform.auth.model.User;
import com.example.health_platform.modules.file.DTO.FileUploadResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    FileUploadResponseDTO uploadMedicalFile(User user, MultipartFile file);

    FileUploadResponseDTO uploadLabFile(User user, MultipartFile file);

    List<FileUploadResponseDTO> getUserFiles(User user, String category);
}
