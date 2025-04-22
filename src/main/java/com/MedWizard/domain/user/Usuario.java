package com.MedWizard.domain.user;

import com.MedWizard.config.exception.BusinnesRuleException;
import com.MedWizard.domain.profile.Perfil;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="usuarios")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeCompleto;
    private String email;
    private String senha;
    private String nomeUsuario;
    private Boolean verificado;
    private String token;
    private LocalDateTime expiracaoToken;
    private Boolean ativo;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_perfis",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "perfil_id"))
    private List<Perfil> perfils = new ArrayList<>();

    @Deprecated
    public Usuario(){}

    public Usuario(DataCreateUsuario dados, String senhaCriptografada, Perfil perfil) {
        this.nomeCompleto = dados.nomeCompleto();
        this.email = dados.email();
        this.senha = senhaCriptografada;
        this.nomeUsuario = dados.nomeUsuario();
        this.verificado = false;
        this.token = UUID.randomUUID().toString();
        this.expiracaoToken = LocalDateTime.now().plusMinutes(30);
        this.ativo = false;
        this.perfils.add(perfil);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return (Collection<? extends GrantedAuthority>) perfils;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }


    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public void verificar() {
        if(expiracaoToken.isBefore(LocalDateTime.now())){
            throw new BusinnesRuleException("Link de verificação expirou!");
        }
        this.verificado = true;
        this.ativo = true;
        this.token = null;
        this.expiracaoToken = null;
    }

    public void desativar() {
        this.ativo = false;
    }

    public Usuario alterarDados(DataEditUsuario dados) {
        if(dados.nomeUsuario() != null){
            this.nomeUsuario = dados.nomeUsuario();
        }
        return this;
    }

    public void alterarSenha(String senhaCriptografada) {
        this.senha = senhaCriptografada;
    }

    public void adicionarPerfil(Perfil perfil) {
        this.perfils.add(perfil);
    }

    public void reativar() {
        this.ativo = true;
    }

}
