package com.startup.vanguard.dto.pedido;

import com.startup.vanguard.dto.carrinho.EnumStatus;
import com.startup.vanguard.model.Endereco;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record PedidoRequestDTO(
        long idCarrinho,
        long idComprador,
        Endereco enderecoEntrega
) {
}
