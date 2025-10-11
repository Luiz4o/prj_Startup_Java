package com.startup.vanguard.dto.pedido;

import com.startup.vanguard.dto.EnumStatus;
import com.startup.vanguard.model.Carrinho;
import com.startup.vanguard.model.Comprador;
import com.startup.vanguard.model.Endereco;
import jakarta.persistence.*;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Builder
public record PedidoResponseDTO(
        long id,
        Carrinho carrinho,
        Comprador comprador,
        BigDecimal valor_total,
        EnumStatus statusPedido,
        OffsetDateTime dataPedido,
        Endereco enderecoEntrega,
        OffsetDateTime ultimaAtualizacao
) {
}
