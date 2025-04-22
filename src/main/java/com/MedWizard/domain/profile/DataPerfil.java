package com.MedWizard.domain.profile;

import jakarta.validation.constraints.NotNull;

public record DataPerfil(
        @NotNull PerfilName perfilName
) {
}
