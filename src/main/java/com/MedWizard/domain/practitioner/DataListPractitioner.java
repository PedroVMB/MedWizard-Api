package com.MedWizard.domain.practitioner;

import com.MedWizard.domain.user.User;

public record DataListPractitioner(
        Long id,
        String numeroLicenca,
        Specialty especialidade,
        String nomeClinica,
        String nomeUsuario
) {
    public DataListPractitioner(Practitioner practitioner) {
        this(
                practitioner.getId(),
                practitioner.getNumeroLicenca(),
                practitioner.getEspecialidade(),
                practitioner.getNomeClinica(),
                practitioner.getUsuario().getNomeUsuario()  // Supondo que o método `getNomeUsuario` exista em `User`
        );
    }
}
