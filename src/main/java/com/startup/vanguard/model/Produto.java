package com.startup.vanguard.model;

import com.startup.vanguard.dto.produto.ProdutoCreateDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "id_lojista", nullable = false)
    private Lojista lojista;

    @OneToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private int quantidadeEstoque;

//    Adicionar um codigo unico como se fosse um codigo de barras caso a loja ja implemente um sistema de verificação de item
//    private String codigoUnico


    public Produto(Lojista lojista, Categoria categoria, ProdutoCreateDTO produtoCreateDTO) {
        this.lojista = lojista;
        this.categoria = categoria;
        this.nome = produtoCreateDTO.nome();
        this.descricao = produtoCreateDTO.descricao();
        this.price = produtoCreateDTO.price();
        this.quantidadeEstoque = produtoCreateDTO.quantidadeEstoque();
    }

    public Produto(Lojista lojista, Categoria categoria, String nome, String descricao, BigDecimal price, int quantidadeEstoque) {
        this.lojista = lojista;
        this.categoria = categoria;
        this.nome = nome;
        this.descricao = descricao;
        this.price = price;
        this.quantidadeEstoque = quantidadeEstoque;
    }

}
