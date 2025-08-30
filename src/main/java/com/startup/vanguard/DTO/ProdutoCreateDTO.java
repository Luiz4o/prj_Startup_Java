package com.startup.vanguard.DTO;

import java.math.BigDecimal;

public record ProdutoCreateDTO (
    long id_lojista,
    long id_categoria,
    String nome,
    String descricao,
    BigDecimal price,
    int quantidadeEstoque
){
}
