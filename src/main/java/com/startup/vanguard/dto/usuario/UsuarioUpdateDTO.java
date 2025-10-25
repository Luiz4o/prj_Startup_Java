package com.startup.vanguard.dto.usuario;

public record UsuarioUpdateDTO(
        String email,
        String telefone,
        String nomeCompleto
) {}