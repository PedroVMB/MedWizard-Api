package com.MedWizard.domain.user;

import jakarta.validation.constraints.NotBlank;

public record DataChangePassword(@NotBlank String currentPassword,
                                 @NotBlank String newPassword,
                                 @NotBlank String newPasswordConfirmation) {
}
