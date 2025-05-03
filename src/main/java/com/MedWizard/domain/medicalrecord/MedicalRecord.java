package com.MedWizard.domain.medicalrecord;

import com.MedWizard.domain.patient.Patient;
import com.MedWizard.domain.practitioner.Practitioner;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "medical_records",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"paciente_id", "medico_id"})
        }
)
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Patient paciente;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id", nullable = false)
    private Practitioner medico;

    @Column(name = "diagnostico")
    private String diagnostico;

    @Column(name = "tratamento")
    private String tratamento;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    @Deprecated
    public MedicalRecord() {}

    public MedicalRecord(Patient paciente, Practitioner medico, String diagnostico, String tratamento) {
        this.paciente = paciente;
        this.medico = medico;
        this.diagnostico = diagnostico;
        this.tratamento = tratamento;
        this.criadoEm = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Patient getPaciente() {
        return paciente;
    }

    public Practitioner getMedico() {
        return medico;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String getTratamento() {
        return tratamento;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setPaciente(Patient paciente) {
        this.paciente = paciente;
    }

    public void setMedico(Practitioner medico) {
        this.medico = medico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public void setTratamento(String tratamento) {
        this.tratamento = tratamento;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }
}
