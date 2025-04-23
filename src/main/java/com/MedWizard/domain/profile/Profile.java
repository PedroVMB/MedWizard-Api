package com.MedWizard.domain.profile;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "perfis")
public class Profile implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ProfileName nome;

    @Override
    public String getAuthority() {
        return "ROLE_" + nome;
    }
}
