package com.MedWizard.domain.medicalrecord;

import jakarta.validation.constraints.NotBlank;

public record DataCreateMedicalRecord(@NotBlank Long pacienteId, @NotBlank  Long medicoId, String diagnostico, @NotBlank String tratamento) {
}
