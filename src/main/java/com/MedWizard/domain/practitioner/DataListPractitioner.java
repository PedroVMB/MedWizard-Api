package com.MedWizard.domain.practitioner;

import com.MedWizard.domain.user.User;

public record DataListPractitioner(
        Long id,
        String numeroLicenca,
        Specialty especialidade,
        String nomeClinica,
        String nomeCompleto
) {
    public DataListPractitioner(Practitioner practitioner) {
        this(
                practitioner.getId(),
                practitioner.getNumeroLicenca(),
                practitioner.getEspecialidade(),
                practitioner.getNomeClinica(),
                practitioner.getUsuario().getNomeCompleto()  // Supondo que o método `getNomeUsuario` exista em `User`
        );
    }
}
