package com.startup.vanguard.dto.comprador;

public record CompradorCreateDTO(
        String email,
        String password,
        String telefone,
        String nomeCompleto,
        String documento,
        String tipoPessoa
) {}
