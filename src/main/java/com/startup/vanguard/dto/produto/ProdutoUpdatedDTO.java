package com.startup.vanguard.dto.produto;

import com.startup.vanguard.model.Produto;

import java.math.BigDecimal;

public record ProdutoUpdatedDTO (
        long id,
        String nome,
        String descricao,
        BigDecimal preco,
        int quantidadeEstoque,
        String nomeFoto,
        String nomeDocumento
){
    public ProdutoUpdatedDTO (Produto p){
        this(p.getId(),p.getNome(),p.getDescricao(),p.getPreco(),p.getQuantidadeEstoque(),p.getNomeFoto(),p.getNomeDocumento());
    }
}