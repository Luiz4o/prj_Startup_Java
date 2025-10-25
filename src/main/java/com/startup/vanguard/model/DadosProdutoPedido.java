package com.startup.vanguard.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Builder
public class DadosProdutoPedido {
    @Column(name = "id_produto_original")
    private Long idProdutoOriginal;

    @Column(name = "nome_produto")
    private String nome;

    @Column(name = "descricao_produto")
    private String descricao;

    @Column(name = "preco_na_compra")
    private BigDecimal precoNaCompra;

    @Column(name = "nome_foto")
    private String nomeFoto;

    @Column(name = "referencia_foto")
    private String referenciaFoto;

    @Column(name = "nome_documento")
    private String nomeDocumento;

    @Column(name = "referencia_doc")
    private String referenciaDoc;

    @Column(name = "nome_categoria")
    private String nomeCategoria;

    @Column(name = "nome_lojista")
    private String nomeLojista;

}
