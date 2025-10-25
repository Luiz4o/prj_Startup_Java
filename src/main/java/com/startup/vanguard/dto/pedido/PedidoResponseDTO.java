package com.startup.vanguard.dto.pedido;

import com.startup.vanguard.dto.usuario.UsuarioResponseDTO;
import com.startup.vanguard.model.Endereco;
import com.startup.vanguard.model.PedidoItem;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Builder
public record PedidoResponseDTO(
        long id,
        UsuarioResponseDTO comprador,
        BigDecimal valor_total,
        String statusPedido,
        OffsetDateTime dataPedido,
        Endereco enderecoEntrega,
        OffsetDateTime ultimaAtualizacao,
        List<PedidoItem> itens
) {
}
