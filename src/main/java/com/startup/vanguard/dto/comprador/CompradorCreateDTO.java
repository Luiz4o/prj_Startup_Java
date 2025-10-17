package com.startup.vanguard.dto.comprador;

import com.startup.vanguard.model.Endereco;

public record CompradorCreateDTO(
        String email,
        String password,
        String telefone,
        String nomeCompleto,
        String documento,
        String tipoPessoa,
        Endereco endereco
) {}
