package com.startup.vanguard.dto.usuario;

import com.startup.vanguard.model.Usuario;
import com.startup.vanguard.model.Endereco;

import java.time.OffsetDateTime;

public record UsuarioResponseDTO(
        Long id,
        String email,
        String telefone,
        String nomeCompleto,
        String documento,
        String tipoPessoa,
        boolean isAtivo,
        OffsetDateTime dataCriacao,
        Endereco endereco
) {
    public UsuarioResponseDTO(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getNomeCompleto(),
                usuario.getDocumento(),
                usuario.getTipoPessoa(),
                usuario.isAtivo(),
                usuario.getData_criacao(),
                usuario.getEndereco()
        );
    }
}