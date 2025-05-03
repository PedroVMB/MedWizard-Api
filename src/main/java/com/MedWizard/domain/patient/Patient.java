package com.MedWizard.domain.patient;

import com.MedWizard.domain.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User usuario;

    @Column(name = "plano_saude")
    private String planoSaude;

    @Column(name = "contato_emergencia")
    private String contatoEmergencia;

    @Deprecated
    public Patient() {}

    public Patient(User usuario, String planoSaude, String contatoEmergencia) {
        this.usuario = usuario;
        this.planoSaude = planoSaude;
        this.contatoEmergencia = contatoEmergencia;
    }

    public Long getId() {
        return id;
    }

    public User getUsuario() {
        return usuario;
    }

    public String getPlanoSaude() {
        return planoSaude;
    }

    public String getContatoEmergencia() {
        return contatoEmergencia;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public void setPlanoSaude(String planoSaude) {
        this.planoSaude = planoSaude;
    }

    public void setContatoEmergencia(String contatoEmergencia) {
        this.contatoEmergencia = contatoEmergencia;
    }
}
