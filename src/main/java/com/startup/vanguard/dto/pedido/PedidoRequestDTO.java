package com.startup.vanguard.dto.pedido;

import com.startup.vanguard.dto.EnumStatus;
import com.startup.vanguard.model.Carrinho;
import com.startup.vanguard.model.Comprador;
import com.startup.vanguard.model.Endereco;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record PedidoRequestDTO(
        long id,
        long idCarrinho,
        long idComprador,
        BigDecimal valor_total,
        EnumStatus statusPedido,
        OffsetDateTime dataPedido,
        Endereco enderecoEntrega,
        OffsetDateTime ultimaAtualizacao
) {
}
