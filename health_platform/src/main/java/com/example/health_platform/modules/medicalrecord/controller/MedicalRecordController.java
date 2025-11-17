
package com.example.health_platform.modules.medicalrecord.controller;

import com.example.health_platform.modules.medicalrecord.DTO.MedicalRecordRequest;
import com.example.health_platform.modules.medicalrecord.DTO.MedicalRecordResponse;
import com.example.health_platform.modules.medicalrecord.model.MedicalRecord;
import com.example.health_platform.modules.medicalrecord.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;




@RestController
@RequestMapping("/medical/record")
@RequiredArgsConstructor
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    @PostMapping("/create")
    public ResponseEntity<MedicalRecordResponse> createRecord(@RequestBody MedicalRecordRequest request) {
        MedicalRecordResponse response = medicalRecordService.createOrUpdateRecord(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/dto/{recordId}")
    public ResponseEntity<MedicalRecordResponse> getRecordById(@PathVariable Long recordId) {
        MedicalRecordResponse response = medicalRecordService.getRecordById(recordId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/entity/{recordId}")
    public MedicalRecord getRecord(@PathVariable Long recordId) {
        return medicalRecordService.getMedicalRecordById(recordId);
    }

    @GetMapping("/list/{userId}")
    public ResponseEntity<List<MedicalRecordResponse>> listUserRecords(@PathVariable Long userId) {
        List<MedicalRecordResponse> records = medicalRecordService.listRecordsByUser(userId);
        return ResponseEntity.ok(records);
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<?> getMedicalProfile(@PathVariable Long userId) {
        return ResponseEntity.ok(medicalRecordService.getMedicalProfileByUser(userId));
    }

    @DeleteMapping("/{recordId}")
    public String deleteRecord(@PathVariable Long recordId) {
        medicalRecordService.deleteMedicalRecord(recordId);
        return "Medical record deleted successfully";
    }
}
