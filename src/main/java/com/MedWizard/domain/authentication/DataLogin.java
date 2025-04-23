package com.MedWizard.domain.authentication;

import jakarta.validation.constraints.NotBlank;

public record DataLogin(@NotBlank String email,
                        @NotBlank String senha) {
}
