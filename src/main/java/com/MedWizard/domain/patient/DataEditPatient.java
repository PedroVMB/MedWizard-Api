package com.MedWizard.domain.patient;

import jakarta.validation.constraints.NotBlank;

public record DataEditPatient(
        String planoSaude,
        String contatoEmergencia
) {
}
