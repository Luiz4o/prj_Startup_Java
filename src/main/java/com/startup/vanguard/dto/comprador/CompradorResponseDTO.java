package com.startup.vanguard.dto.comprador;

import com.startup.vanguard.model.Comprador;

import java.time.OffsetDateTime;

public record CompradorResponseDTO(
        Long id,
        String email,
        String telefone,
        String nomeCompleto,
        String documento,
        String tipoPessoa,
        boolean isAtivo,
        OffsetDateTime dataCriacao
) {
    public CompradorResponseDTO(Comprador comprador) {
        this(
                comprador.getId(),
                comprador.getEmail(),
                comprador.getTelefone(),
                comprador.getNomeCompleto(),
                comprador.getDocumento(),
                comprador.getTipoPessoa(),
                comprador.isAtivo(),
                comprador.getData_criacao()
        );
    }
}