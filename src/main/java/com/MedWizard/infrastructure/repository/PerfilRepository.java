package com.MedWizard.infrastructure.repository;

import com.MedWizard.domain.profile.Perfil;
import com.MedWizard.domain.profile.PerfilName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    Perfil findByNome(PerfilName perfilName);
}
