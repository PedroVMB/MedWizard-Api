package com.MedWizard.Web.Controllers;

import com.MedWizard.domain.medicalrecord.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prontuario")
public class MedicalRecordController {
    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody DataCreateMedicalRecord data) {
        medicalRecordService.create(data);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<DataListMedicalRecord>> listAll() {
        return ResponseEntity.ok(medicalRecordService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataListMedicalRecord> getById(@PathVariable Long id) {
        return ResponseEntity.ok(medicalRecordService.findById(id));
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody DataEditMedicalRecord data) {
        medicalRecordService.update(data);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        medicalRecordService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
