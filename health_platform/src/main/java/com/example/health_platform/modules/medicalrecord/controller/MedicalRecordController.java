package com.example.health_platform.modules.medicalrecord.controller;



import com.example.health_platform.modules.medicalrecord.DTO.MedicalRecordRequest;
import com.example.health_platform.modules.medicalrecord.DTO.MedicalRecordResponse;
import com.example.health_platform.modules.medicalrecord.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medical/record")
@RequiredArgsConstructor
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    // ✅ Create or update a medical record
    @PostMapping("/create")
    public ResponseEntity<MedicalRecordResponse> createRecord(@RequestBody MedicalRecordRequest request) {
        MedicalRecordResponse response = medicalRecordService.createOrUpdateRecord(request);
        return ResponseEntity.ok(response);
    }

    // ✅ Get a medical record by its ID
    @GetMapping("/{recordId}")
    public ResponseEntity<MedicalRecordResponse> getRecordById(@PathVariable Long recordId) {
        MedicalRecordResponse response = medicalRecordService.getRecordById(recordId);
        return ResponseEntity.ok(response);
    }

    // ✅ List all medical records of a user
    @GetMapping("/list/{userId}")
    public ResponseEntity<List<MedicalRecordResponse>> listUserRecords(@PathVariable Long userId) {
        List<MedicalRecordResponse> records = medicalRecordService.listRecordsByUser(userId);
        return ResponseEntity.ok(records);
    }

    // ✅ Get medical profile for a user (optional)
    @GetMapping("/profile/{userId}")
    public ResponseEntity<?> getMedicalProfile(@PathVariable Long userId) {
        return ResponseEntity.ok(medicalRecordService.getMedicalProfileByUser(userId));
    }
}
