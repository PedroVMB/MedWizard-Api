package com.MedWizard.domain.practitioner;

import com.MedWizard.domain.user.User;
import jakarta.validation.constraints.NotBlank;

public record DataCreatePracticioner(
        @NotBlank String numeroLicenca,
        @NotBlank Specialty especialidade,
        @NotBlank String nomeClinica,
        @NotBlank Long userId // Passando apenas o ID do usuário
) {}
