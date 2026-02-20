package com.example.health_platform.modules.user.dto;



import org.springframework.web.multipart.MultipartFile;

public class ProfilePhotoUploadRequest {
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
