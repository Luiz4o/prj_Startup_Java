package com.startup.vanguard.dto.pedido;

import com.startup.vanguard.dto.EnumStatus;
import com.startup.vanguard.model.Carrinho;
import com.startup.vanguard.model.Comprador;
import com.startup.vanguard.model.Endereco;
import com.startup.vanguard.model.PedidoItem;
import jakarta.persistence.*;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Builder
public record PedidoResponseDTO(
        long id,
        Carrinho carrinho,
        Comprador comprador,
        BigDecimal valor_total,
        EnumStatus statusPedido,
        OffsetDateTime dataPedido,
        Endereco enderecoEntrega,
        OffsetDateTime ultimaAtualizacao,
        List<PedidoItem> itens
) {
}
