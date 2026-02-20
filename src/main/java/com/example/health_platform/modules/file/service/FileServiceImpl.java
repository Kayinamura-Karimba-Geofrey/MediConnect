package com.example.health_platform.modules.file.service;

import com.example.health_platform.auth.model.User;
import com.example.health_platform.modules.file.DTO.FileUploadResponseDTO;
import com.example.health_platform.modules.file.model.MedicalFile;
import com.example.health_platform.modules.file.repository.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public FileUploadResponseDTO uploadMedicalFile(User user, MultipartFile file) {
        return saveFile(user, file, "medical");
    }

    @Override
    public FileUploadResponseDTO uploadLabFile(User user, MultipartFile file) {
        return saveFile(user, file, "lab");
    }

    private FileUploadResponseDTO saveFile(User user, MultipartFile file, String category) {
        try {
            MedicalFile medicalFile = MedicalFile.builder()
                    .fileName(file.getOriginalFilename())
                    .fileType(file.getContentType())
                    .data(file.getBytes())
                    .category(category)
                    .user(user)
                    .build();

            MedicalFile saved = fileRepository.save(medicalFile);

            FileUploadResponseDTO response = new FileUploadResponseDTO();
            response.setId(saved.getId());
            response.setFileName(saved.getFileName());
            response.setFileType(saved.getFileType());
            response.setCategory(saved.getCategory());
            response.setMessage("File uploaded successfully");
            return response;

        } catch (IOException e) {
            throw new RuntimeException("Failed to store file: " + e.getMessage());
        }
    }

    @Override
    public List<FileUploadResponseDTO> getUserFiles(User user, String category) {
        return fileRepository.findByUserAndCategory(user, category)
                .stream()
                .map(file -> {
                    FileUploadResponseDTO dto = new FileUploadResponseDTO();
                    dto.setId(file.getId());
                    dto.setFileName(file.getFileName());
                    dto.setFileType(file.getFileType());
                    dto.setCategory(file.getCategory());
                    dto.setMessage("File retrieved successfully");
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
