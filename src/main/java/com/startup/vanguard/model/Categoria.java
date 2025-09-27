package com.startup.vanguard.model;

import com.startup.vanguard.dto.categoria.CategoriaRequestDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    public Categoria(CategoriaRequestDTO categoriaRequestDTO) {
        this.nome = categoriaRequestDTO.nome();
        this.descricao = categoriaRequestDTO.descricao();
    }
}
