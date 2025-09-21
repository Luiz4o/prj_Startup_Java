package com.startup.vanguard.model;

import com.startup.vanguard.dto.EnumStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carrinhos")
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_comprador", nullable = false)
    private Comprador comprador;

    private OffsetDateTime dataCriacao;

    private String status;

    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL)
    private List<CarrinhoItem> itens = new ArrayList<>();
}
