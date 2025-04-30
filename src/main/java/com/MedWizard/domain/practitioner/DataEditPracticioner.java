package com.MedWizard.domain.practitioner;

import com.MedWizard.domain.user.User;

public record DataEditPracticioner(
        String numeroLicenca,
        String nomeClinica,
        Specialty especialidade,
        User user
) {
}
