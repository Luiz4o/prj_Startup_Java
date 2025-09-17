package com.startup.vanguard.DTO.produto;

import java.math.BigDecimal;

public record ProdutoUpdateDTO (
        Long id,
        Long id_categoria,
        String nome,
        String descricao,
        BigDecimal price,
        Integer quantidadeEstoque
){
}
