package com.startup.vanguard.dto.carrinho;

import com.startup.vanguard.dto.EnumStatus;
import com.startup.vanguard.model.Carrinho;

import java.time.OffsetDateTime;

public record CarrinhoResponseDTO(
        Long id,
        EnumStatus status,
        Long idComprador,
        OffsetDateTime dataCriacao
//        List<CarrinhoItemResponseDTO> itens
) {
    public CarrinhoResponseDTO(Carrinho carrinho) {
        this(
                carrinho.getId(),
                carrinho.getStatus(),
                carrinho.getComprador().getId(),
                carrinho.getDataCriacao()
//                carrinho.getItens().stream().map(CarrinhoItemResponseDTO::new).toList()
        );
    }
}