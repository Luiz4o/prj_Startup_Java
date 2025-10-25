package com.startup.vanguard.dto.carrinho;

import com.startup.vanguard.dto.produto.ProdutoCarrinhoDTO;
import com.startup.vanguard.dto.produto.ProdutoResponseDTO;
import com.startup.vanguard.dto.usuario.UsuarioResponseDTO;
import com.startup.vanguard.model.Carrinho;
import com.startup.vanguard.model.CarrinhoItem;
import com.startup.vanguard.model.Produto;
import com.startup.vanguard.model.Usuario;

import java.time.OffsetDateTime;
import java.util.List;

public record CarrinhoResponseDTO(
        Long id,
        String status,
        UsuarioResponseDTO usuario,
        OffsetDateTime dataCriacao,
        List<ProdutoCarrinhoDTO> produtos
) {
    public CarrinhoResponseDTO(Carrinho carrinho) {
        this(
                carrinho.getId(),
                carrinho.getStatus(),
                new UsuarioResponseDTO(carrinho.getUsuario()),
                carrinho.getDataCriacao(),
                carrinho.getItens().stream()
                        .map(cItem -> new ProdutoCarrinhoDTO(cItem.getProduto()))
                        .toList()
        );
    }
}
