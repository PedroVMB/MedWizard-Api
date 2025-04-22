package com.MedWizard.domain.profile;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "perfis")
public class Perfil implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private PerfilName nome;

    @Override
    public String getAuthority() {
        return "ROLE_" + nome;
    }
}
