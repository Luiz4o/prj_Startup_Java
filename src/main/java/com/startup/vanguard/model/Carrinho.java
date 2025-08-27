package com.startup.vanguard.model;

import com.startup.vanguard.DTO.EnumStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.time.OffsetDateTime;

@Entity
@Table(name = "carrinhos")
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "id_comprador", nullable = false)
    private Comprador comprador;

    @Column(nullable = false)
    private OffsetDateTime dataCriacao;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumStatus status;
}
