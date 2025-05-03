package com.MedWizard.domain.medicalrecord;

import com.MedWizard.domain.patient.Patient;
import com.MedWizard.domain.patient.PatientRepository;
import com.MedWizard.domain.practitioner.Practitioner;
import com.MedWizard.domain.practitioner.PractitionerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final PatientRepository patientRepository;
    private final PractitionerRepository practitionerRepository;

    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository,
                                PatientRepository patientRepository,
                                PractitionerRepository practitionerRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.patientRepository = patientRepository;
        this.practitionerRepository = practitionerRepository;
    }

    @Transactional
    public void create(DataCreateMedicalRecord data) {
        Patient paciente = patientRepository.findById(data.pacienteId())
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));
        Practitioner medico = practitionerRepository.findById(data.medicoId())
                .orElseThrow(() -> new EntityNotFoundException("Médico não encontrado"));

        MedicalRecord record = new MedicalRecord(paciente, medico, data.diagnostico(), data.tratamento());
        medicalRecordRepository.save(record);
    }

    public List<DataListMedicalRecord> listAll() {
        return medicalRecordRepository.findAll().stream().map(record ->
                new DataListMedicalRecord(
                        record.getId(),
                        record.getPaciente().getUsuario().getNomeCompleto(),
                        record.getMedico().getUsuario().getNomeCompleto(),
                        record.getDiagnostico(),
                        record.getTratamento(),
                        record.getCriadoEm()
                )
        ).toList();
    }

    public DataListMedicalRecord findById(Long id) {
        MedicalRecord record = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prontuário não encontrado"));
        return new DataListMedicalRecord(
                record.getId(),
                record.getPaciente().getUsuario().getNomeCompleto(),
                record.getMedico().getUsuario().getNomeCompleto(),
                record.getDiagnostico(),
                record.getTratamento(),
                record.getCriadoEm()
        );
    }

    @Transactional
    public void update(DataEditMedicalRecord data) {
        MedicalRecord record = medicalRecordRepository.findById(data.id)
                .orElseThrow(() -> new EntityNotFoundException("Prontuário não encontrado"));
        record.setDiagnostico(data.diagnostico);
        record.setTratamento(data.tratamento);
    }

    @Transactional
    public void delete(Long id) {
        if (!medicalRecordRepository.existsById(id)) {
            throw new EntityNotFoundException("Prontuário não encontrado");
        }
        medicalRecordRepository.deleteById(id);
    }
}
