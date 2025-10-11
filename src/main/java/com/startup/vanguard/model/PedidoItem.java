package com.startup.vanguard.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "pedido_itens")
@Data
@Builder
public class PedidoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido pedido;

    // TROCAR ESTE CARA INVES DE ASSOCIAR UM PRODUTO FAZER A CRIAÇÃO DE UM NOVO PRODUTO? OU TALVEZ COMO AQUI JA SERIA UM ITEM DO PEDIDO TER AS INFORMAÇÔES COMPLETAS INVES DE RELACIONAR
    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    private int quantidade;

    private BigDecimal precoUnitario;
}
