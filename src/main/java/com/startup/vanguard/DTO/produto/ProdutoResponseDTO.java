package com.startup.vanguard.dto.produto;

import com.startup.vanguard.model.Produto;

import java.math.BigDecimal;

public record ProdutoResponseDTO (
        long id,
//        Lojista lojista,
//        Categoria categoria,
        String nome,
        String descricao,
        BigDecimal price,
        int quantidadeEstoque
){
    public ProdutoResponseDTO (Produto p){
        this(p.getId(),p.getNome(),p.getDescricao(),p.getPrice(),p.getQuantidadeEstoque());
    }
}
