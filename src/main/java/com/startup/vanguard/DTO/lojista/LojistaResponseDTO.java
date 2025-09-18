package com.startup.vanguard.dto.lojista;

import com.startup.vanguard.model.Lojista;

import java.time.OffsetDateTime;

public record LojistaResponseDTO (
        Long id,
        String email,
        String telefone,
        String razaoSocial,
        String cnpj,
        boolean isAtivo,
        OffsetDateTime dataCriacao
) {
public LojistaResponseDTO(Lojista lojista) {
    this(
            lojista.getId(),
            lojista.getEmail(),
            lojista.getTelefone(),
            lojista.getRazaoSocial(),
            lojista.getCNPJ(),
            lojista.isAtivo(),
            lojista.getData_criacao()
    );
}
}
