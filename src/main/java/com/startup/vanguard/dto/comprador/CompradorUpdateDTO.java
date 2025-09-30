package com.startup.vanguard.dto.comprador;

public record CompradorUpdateDTO(
        String email,
        String telefone,
        String nomeCompleto
) {}