package com.MedWizard.domain.patient;

public record DataListPatient(
        Long id,
        String nomeCompleto,
        String planoSaude,
        String contatoEmergencia
) {
    public DataListPatient(Patient patient){
        this(
                patient.getId(),
                patient.getUsuario().getNomeCompleto(),
                patient.getPlanoSaude(),
                patient.getPlanoSaude()
        );
    }

}
