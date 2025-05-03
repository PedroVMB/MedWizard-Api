package com.MedWizard.domain.patient;

import com.MedWizard.domain.user.User;
import jakarta.validation.constraints.NotBlank;

public record DataCreatePatient(
        @NotBlank User user,
        @NotBlank String planoSaude,
        @NotBlank String contatoEmergencia
        ) {
}
