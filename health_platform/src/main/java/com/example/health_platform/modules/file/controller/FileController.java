package com.example.health_platform.modules.file.controller;

import com.example.health_platform.auth.model.User;
import com.example.health_platform.modules.file.DTO.FileUploadResponseDTO;
import com.example.health_platform.modules.file.service.FileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload/medical")
    public FileUploadResponseDTO uploadMedical(@RequestAttribute("user") User user,
                                               @RequestParam("file") MultipartFile file) {
        return fileService.uploadMedicalFile(user, file);
    }

    @PostMapping("/upload/lab")
    public FileUploadResponseDTO uploadLab(@RequestAttribute("user") User user,
                                           @RequestParam("file") MultipartFile file) {
        return fileService.uploadLabFile(user, file);
    }

    @GetMapping("/my/{category}")
    public List<FileUploadResponseDTO> getUserFiles(@RequestAttribute("user") User user,
                                                    @PathVariable String category) {
        return fileService.getUserFiles(user, category);
    }
}
