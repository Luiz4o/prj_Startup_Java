package com.startup.vanguard.dto.produto;

import com.startup.vanguard.model.Produto;

import java.math.BigDecimal;

public record ProdutoResponseDTO (
        long id,
        String nome,
        String descricao,
        BigDecimal preco,
        int quantidadeEstoque,
        String nomeFoto,
        String nomeDocumento,
        String urlFoto,
        String urlDocumento
){
    public ProdutoResponseDTO (Produto p, String urlFoto, String urlDocumento){
        this(p.getId(),p.getNome(),p.getDescricao(),p.getPreco(),p.getQuantidadeEstoque(),p.getNomeFoto(),p.getNomeDocumento(), urlFoto, urlDocumento);
    }
}
