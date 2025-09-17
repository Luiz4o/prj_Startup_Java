package com.startup.vanguard.model;

import com.startup.vanguard.DTO.produto.ProdutoCreateDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Lojista getLojista() {
        return lojista;
    }

    public void setLojista(Lojista lojista) {
        this.lojista = lojista;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }
}
