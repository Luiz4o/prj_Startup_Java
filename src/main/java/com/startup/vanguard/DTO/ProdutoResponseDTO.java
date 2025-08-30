package com.startup.vanguard.DTO;

import com.startup.vanguard.model.Categoria;
import com.startup.vanguard.model.Lojista;
import com.startup.vanguard.model.Produto;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

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
