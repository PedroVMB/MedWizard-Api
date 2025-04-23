package com.MedWizard.domain.user;
public record DataListUser(Long id,
                                   String email,
                                   String nomeCompleto,
                                   String nomeUsuario
) {
    public DataListUser(User usuario) {
        this(usuario.getId(), usuario.getUsername(),
                usuario.getNomeCompleto(), usuario.getNomeUsuario());
    }
}