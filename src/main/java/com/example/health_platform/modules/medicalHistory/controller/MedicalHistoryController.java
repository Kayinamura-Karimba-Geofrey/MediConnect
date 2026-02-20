
package com.example.health_platform.modules.medicalHistory.controller;

import com.example.health_platform.modules.medicalHistory.model.MedicalHistory;
import com.example.health_platform.modules.medicalHistory.service.MedicalHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/medical-history")
public class MedicalHistoryController {

    private final MedicalHistoryService service;

    public MedicalHistoryController(MedicalHistoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MedicalHistory> createHistory(@RequestBody MedicalHistory history) {
        return ResponseEntity.ok(service.save(history));
    }

    @GetMapping
    public ResponseEntity<List<MedicalHistory>> getAllHistories() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalHistory> getHistoryById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistory(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/patient/{name}")
    public ResponseEntity<List<MedicalHistory>> getHistoryByPatient(@PathVariable String name) {
        return ResponseEntity.ok(service.getByPatientName(name));
    }
}
