package com.example.health_platform.modules.file.repository;



import com.example.health_platform.modules.file.model.MedicalFile;
import com.example.health_platform.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<MedicalFile, Long> {
    List<MedicalFile> findByUser(User user);
    List<MedicalFile> findByUserAndCategory(User user, String category);
}
