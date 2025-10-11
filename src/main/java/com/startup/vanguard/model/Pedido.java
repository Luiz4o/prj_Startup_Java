package com.startup.vanguard.model;

import com.startup.vanguard.dto.EnumStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Data
@Builder
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "id_carrinho", nullable = false)
    private Carrinho carrinho;

    @ManyToOne
    @JoinColumn(name = "id_comprador", nullable = false)
    private Comprador comprador;

    private BigDecimal valor_total;

    @Enumerated(EnumType.STRING)
    private EnumStatus statusPedido;

    private OffsetDateTime dataPedido;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<PedidoItem> pedidoItems;

    @Embedded
    private Endereco enderecoEntrega;

    private OffsetDateTime ultimaAtualizacao;
}
