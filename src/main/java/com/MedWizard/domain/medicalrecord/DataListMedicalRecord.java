package com.MedWizard.domain.medicalrecord;

import java.time.LocalDateTime;

public record DataListMedicalRecord(Long id, String nomePaciente, String nomeMedico, String diagnostico, String tratamento, LocalDateTime criadoEm) {
    public DataListMedicalRecord(MedicalRecord medicalRecord){
        this(medicalRecord.getId(), medicalRecord.getPaciente().getUsuario().getNomeCompleto(), medicalRecord.getMedico().getUsuario().getNomeCompleto(), medicalRecord.getDiagnostico(), medicalRecord.getTratamento(), medicalRecord.getCriadoEm());
    }
}
