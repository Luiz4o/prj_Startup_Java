package com.startup.vanguard.dto.carrinho;

import com.startup.vanguard.model.Carrinho;

import java.time.OffsetDateTime;

public record CarrinhoResponseDTO(
        Long id,
        String status,
        Long idComprador,
        OffsetDateTime dataCriacao
//        List<CarrinhoItemResponseDTO> itens
) {
    public CarrinhoResponseDTO(Carrinho carrinho) {
        this(
                carrinho.getId(),
                carrinho.getStatus(),
                carrinho.getComprador() != null ? carrinho.getComprador().getId() : null,
                carrinho.getDataCriacao()
//                carrinho.getItens().stream().map(CarrinhoItemResponseDTO::new).toList()
        );
    }
}