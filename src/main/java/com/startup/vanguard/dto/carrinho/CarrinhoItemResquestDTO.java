package com.startup.vanguard.dto.carrinho;

import com.startup.vanguard.model.Carrinho;
import com.startup.vanguard.model.Produto;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public record CarrinhoItemResquestDTO(
        Long idProduto,
        int quantidade
) {
}
