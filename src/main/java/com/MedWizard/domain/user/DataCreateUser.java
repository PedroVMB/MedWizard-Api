package com.MedWizard.domain.user;

import jakarta.validation.constraints.NotBlank;

public record DataCreateUser(
        @NotBlank String email,
        @NotBlank String senha,
        @NotBlank String nomeCompleto,
        @NotBlank String nomeUsuario,
        String miniBiografia,
        String biografia
) {
}
