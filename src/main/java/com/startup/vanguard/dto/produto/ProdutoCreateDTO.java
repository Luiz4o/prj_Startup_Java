package com.startup.vanguard.dto.produto;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public record ProdutoCreateDTO (
    long idLojista,
    long idCategoria,
    String nome,
    String descricao,
    BigDecimal preco,
    int quantidadeEstoque
){
}
