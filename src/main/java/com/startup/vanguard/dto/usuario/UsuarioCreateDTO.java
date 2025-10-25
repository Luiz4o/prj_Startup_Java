package com.startup.vanguard.dto.usuario;

import com.startup.vanguard.model.Endereco;

public record UsuarioCreateDTO(
        String email,
        String password,
        String telefone,
        String nomeCompleto,
        String documento,
        String tipoPessoa,
        EnumUsuario tipoUsuario,
        Endereco endereco
) {}
