package com.startup.vanguard.model;

import jakarta.persistence.*;
import org.springframework.validation.annotation.Validated;

import java.time.OffsetDateTime;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private OffsetDateTime data_criacao;
    @Column(nullable = false)
    private String telefone;
}
