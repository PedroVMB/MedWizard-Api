package com.MedWizard.domain.medicalrecord;

import com.MedWizard.domain.patient.Patient;
import com.MedWizard.domain.practitioner.Practitioner;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "medical_records",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"patient_id", "practitioner_id"})
        }
)
public class MedicalRecord{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "practitioner_id", nullable = false)
    private Practitioner practitioner;

    private String diagnosis;

    private String treatment;

    private LocalDateTime createdAt;

    @Deprecated
    public MedicalRecord() {}

    public MedicalRecord(Patient patient, Practitioner practitioner, String diagnosis, String treatment) {
        this.patient = patient;
        this.practitioner = practitioner;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public Practitioner getPractitioner() {
        return practitioner;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
