package com.MedWizard.Web.Controllers;

import com.MedWizard.domain.practitioner.*;
import com.MedWizard.domain.user.DataEditUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import jakarta.validation.Valid;

import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/medico")
public class PractitionerController {

    private final PractitionerService practitionerService;

    public PractitionerController(PractitionerService practitionerService) {
        this.practitionerService = practitionerService;
    }

    // **Cadastrar novo Médico**
    @PostMapping
    public ResponseEntity<DataListPractitioner> register(@RequestBody @Valid DataCreatePracticioner data, UriComponentsBuilder uriBuilder){
        var practitioner = practitionerService.create(data);
        var uri = uriBuilder.path("/{id}").buildAndExpand(practitioner.getId()).toUri();
        return ResponseEntity.created(uri).body(new DataListPractitioner(practitioner));
    }

    // **Buscar Médico por ID**
    @GetMapping("/{id}")
    public ResponseEntity<DataListPractitioner> getById(@PathVariable Long id) {
        Practitioner practitioner = practitionerService.findById(id);
        return ResponseEntity.ok(new DataListPractitioner(practitioner));
    }

    // **Atualizar Médico**
    @PutMapping("/{id}")
    public ResponseEntity<DataListPractitioner> update(@PathVariable Long id, @RequestBody @Valid DataEditPracticioner data) {
        Practitioner practitioner = practitionerService.update(id, data);
        return ResponseEntity.ok(new DataListPractitioner(practitioner));
    }

    // **Excluir Médico**
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        practitionerService.delete(id);
        return ResponseEntity.noContent().build();  // 204 No Content
    }

    @GetMapping
    public ResponseEntity<Iterable<DataListPractitioner>> listAll() {
        Iterable<Practitioner> practitioners = practitionerService.findAll();

        // Convertendo o Iterable para uma List e mapeando para DataListPractitioner
        Iterable<DataListPractitioner> practitionersDto =
                StreamSupport.stream(practitioners.spliterator(), false)
                        .map(DataListPractitioner::new)
                        .toList();

        return ResponseEntity.ok(practitionersDto);
    }

}
