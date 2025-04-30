package com.MedWizard.domain.practitioner;

import com.MedWizard.domain.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "practitioners")
public class Practitioner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User usuario;

    @Column(name = "numero_licenca")
    private String numeroLicenca;

    @Enumerated(EnumType.STRING)
    @Column(name = "especialidade")
    private Specialty especialidade;

    @Column(name = "nome_clinica")
    private String nomeClinica;

    @Deprecated
    public Practitioner() {}

    public Practitioner(User usuario, String numeroLicenca, Specialty especialidade, String nomeClinica) {
        this.usuario = usuario;
        this.numeroLicenca = numeroLicenca;
        this.especialidade = especialidade;
        this.nomeClinica = nomeClinica;
    }

    public Long getId() {
        return id;
    }

    public User getUsuario() {
        return usuario;
    }

    public String getNumeroLicenca() {
        return numeroLicenca;
    }

    public Specialty getEspecialidade() {
        return especialidade;
    }

    public String getNomeClinica() {
        return nomeClinica;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public void setNumeroLicenca(String numeroLicenca) {
        this.numeroLicenca = numeroLicenca;
    }

    public void setEspecialidade(Specialty especialidade) {
        this.especialidade = especialidade;
    }

    public void setNomeClinica(String nomeClinica) {
        this.nomeClinica = nomeClinica;
    }
}
