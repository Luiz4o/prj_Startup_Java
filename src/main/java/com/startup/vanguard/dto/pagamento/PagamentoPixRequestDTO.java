package com.startup.vanguard.dto.pagamento;

import com.startup.vanguard.dto.pedido.PedidoRequestDTO;
import lombok.Data;

public record PagamentoPixRequestDTO (
    Double valor,
    String descricao,
    String email,
    String nome,
    String cpf,
    PedidoRequestDTO pedido
){
}
