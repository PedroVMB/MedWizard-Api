package com.MedWizard.domain.practitioner;

import com.MedWizard.domain.user.DataEditUser;
import com.MedWizard.domain.user.User;
import com.MedWizard.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PractitionerService {
    private final PractitionerRepository practitionerRepository;
    private final UserRepository userRepository;

    public PractitionerService(PractitionerRepository practitionerRepository, UserRepository userRepository) {
        this.practitionerRepository = practitionerRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Practitioner create(DataCreatePracticioner data) {
        // Buscar o usuário pelo ID
        User usuario = userRepository.findById(data.userId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));


        Practitioner practitioner = new Practitioner(
                usuario,
                data.numeroLicenca(),
                data.especialidade(),
                data.nomeClinica()
        );


        return practitionerRepository.save(practitioner);
    }


    public Practitioner findById(Long id) {
        return practitionerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado com o ID: " + id));
    }


    @Transactional
    public Practitioner update(Long id, DataEditPracticioner data) {

        Practitioner practitioner = practitionerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado com o ID: " + id));


        User usuario = userRepository.findById(data.user().getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));


        practitioner.setNumeroLicenca(data.numeroLicenca());
        practitioner.setEspecialidade(data.especialidade());
        practitioner.setNomeClinica(data.nomeClinica());
        practitioner.setUsuario(usuario);

        // Salvando as alterações
        return practitionerRepository.save(practitioner);
    }


    @Transactional
    public void delete(Long id) {
        Practitioner practitioner = practitionerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado com o ID: " + id));
        practitionerRepository.delete(practitioner);
    }

    public Iterable<Practitioner> findAll() {
        return practitionerRepository.findAll();
    }
}
