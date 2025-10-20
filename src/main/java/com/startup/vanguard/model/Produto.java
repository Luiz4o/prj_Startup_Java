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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "produtos")
@Builder
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
    private BigDecimal preco;

    @Column(nullable = false)
    private int quantidadeEstoque;

    private String nomeFoto;

    private String referenciaFoto;

    private String nomeDocumento;

    private String referenciaDoc;

//    Adicionar um codigo unico como se fosse um codigo de barras caso a loja ja implemente um sistema de verificação de item
//    private String codigoUnico


    public Produto(Lojista lojista, Categoria categoria, ProdutoCreateDTO produtoCreateDTO, MultipartFile foto, MultipartFile doc) {
        this.lojista = lojista;
        this.categoria = categoria;
        this.nome = produtoCreateDTO.nome();
        this.descricao = produtoCreateDTO.descricao();
        this.preco = produtoCreateDTO.preco();
        this.quantidadeEstoque = produtoCreateDTO.quantidadeEstoque();
        this.nomeFoto = foto.getOriginalFilename();
        this.nomeDocumento = doc.getOriginalFilename();
    }

    public Produto(Lojista lojista, Categoria categoria, String nome, String descricao, BigDecimal preco, int quantidadeEstoque) {
        this.lojista = lojista;
        this.categoria = categoria;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }

}
