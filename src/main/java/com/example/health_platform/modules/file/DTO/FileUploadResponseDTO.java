package com.example.health_platform.modules.file.DTO;

import lombok.Data;

@Data
public class FileUploadResponseDTO {
    private Long id;
    private String fileName;
    private String fileType;
    private String category;
    private String message;
}
