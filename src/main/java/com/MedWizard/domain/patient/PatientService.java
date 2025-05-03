package com.MedWizard.domain.patient;

import com.MedWizard.domain.user.User;
import com.MedWizard.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    
    public PatientService(PatientRepository patientRepository, UserRepository userRepository) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }
    
    @Transactional
    public Patient create(DataCreatePatient data) {
        // Buscar o usuário pelo ID
        User usuario = userRepository.findById(data.user().getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));


        Patient Patient = new Patient(
                usuario,
                data.planoSaude(),
                data.contatoEmergencia()
        );


        return patientRepository.save(Patient);
    }


    public Patient findById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado com o ID: " + id));
    }


    @Transactional
    public Patient update(Long id, DataEditPatient data) {

        Patient Patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado com o ID: " + id));



        Patient.setPlanoSaude(data.planoSaude());
        Patient.setContatoEmergencia(data.contatoEmergencia());


        // Salvando as alterações
        return patientRepository.save(Patient);
    }


    @Transactional
    public void delete(Long id) {
        Patient Patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado com o ID: " + id));
        patientRepository.delete(Patient);
    }

    public Iterable<Patient> findAll() {
        return patientRepository.findAll();
    }
    
}
