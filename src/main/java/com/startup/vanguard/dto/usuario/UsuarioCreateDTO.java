package com.startup.vanguard.dto.usuario;

import com.startup.vanguard.model.Endereco;
import com.startup.vanguard.validation.annotation.ValidPassword;

public record UsuarioCreateDTO(
        String email,
        @ValidPassword
        String password,
        String telefone,
        String nomeCompleto,
        String documento,
        String tipoPessoa,
        EnumUsuario tipoUsuario,
        Endereco endereco
) {}
