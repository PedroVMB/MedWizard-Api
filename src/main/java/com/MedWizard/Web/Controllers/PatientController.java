package com.MedWizard.Web.Controllers;

import com.MedWizard.domain.patient.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/paciente")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<DataListPatient> register(@RequestBody @Valid DataCreatePatient data, UriComponentsBuilder uriBuilder){
        var patient = patientService.create(data);
        var uri = uriBuilder.path("/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(uri).body(new DataListPatient(patient));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataListPatient> getById(@PathVariable Long id) {
        Patient patient = patientService.findById(id);
        return ResponseEntity.ok(new DataListPatient(patient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataListPatient> update(@PathVariable Long id, @RequestBody @Valid DataEditPatient data) {
        Patient patient = patientService.update(id, data);
        return ResponseEntity.ok(new DataListPatient(patient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        patientService.delete(id);
        return ResponseEntity.noContent().build();  // 204 No Content
    }

    @GetMapping
    public ResponseEntity<Iterable<DataListPatient>> listAll() {
        Iterable<Patient> patients = patientService.findAll();

        // Convertendo o Iterable para uma List e mapeando para DataListPatient
        Iterable<DataListPatient> patientsDto =
                StreamSupport.stream(patients.spliterator(), false)
                        .map(DataListPatient::new)
                        .toList();

        return ResponseEntity.ok(patientsDto);
    }
}
