package com.startup.vanguard.dto.carrinho;

import com.startup.vanguard.model.Produto;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public record CarrinhoItemDTO (
        Long idProduto,
        Long idCarrinho,
        int quantidade
){
}
