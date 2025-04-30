package com.MedWizard.domain.practitioner;

public enum Specialty {
    CARDIOLOGIA("Cardiologia"),
    DERMATOLOGIA("Dermatologia"),
    PEDIATRIA("Pediatria"),
    ORTOPEDIA("Ortopedia"),
    GINECOLOGIA("Ginecologia"),
    PSIQUIATRIA("Psiquiatria"),
    CLINICA_GERAL("Clínica Geral");

    private final String descricao;

    Specialty(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
