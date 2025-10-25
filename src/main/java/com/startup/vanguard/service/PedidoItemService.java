package com.startup.vanguard.service;

import com.startup.vanguard.exception.ResourceNotFoundException;
import com.startup.vanguard.model.CarrinhoItem;
import com.startup.vanguard.model.Pedido;
import com.startup.vanguard.model.PedidoItem;
import com.startup.vanguard.repository.PedidoItemRepository;
import com.startup.vanguard.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.startup.vanguard.model.DadosProdutoPedido;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoItemService {

    private final PedidoItemRepository pedidoItemRepository;

    private final ProdutoRepository produtoRepository;

    public PedidoItemService(PedidoItemRepository pedidoItemRepository, ProdutoRepository produtoRepository) {
        this.pedidoItemRepository = pedidoItemRepository;
        this.produtoRepository = produtoRepository;
    }

   @Transactional
    public List<PedidoItem> createPedidoItemByCarrinhoItem(List<CarrinhoItem> carrinhoItemList, Pedido pedido) {
        return carrinhoItemList.stream()
                .map(cItem -> {
                    var produto = produtoRepository.findById(cItem.getProduto().getId())
                            .orElseThrow(() -> new ResourceNotFoundException("Produto", cItem.getProduto().getId()));

                    var dadosProduto = DadosProdutoPedido.builder()
                            .idProdutoOriginal(produto.getId())
                            .nome(produto.getNome())
                            .nomeCategoria(produto.getCategoria().getNome())
                            .nomeDocumento(produto.getNomeDocumento())
                            .referenciaDoc(produto.getReferenciaDoc())
                            .referenciaFoto(produto.getReferenciaFoto())
                            .nomeLojista(produto.getLojista().getNomeCompleto())
                            .precoNaCompra(produto.getPreco())
                            .descricao(produto.getDescricao())
                            .nomeFoto(produto.getNomeFoto())
                            .build();

                    return pedidoItemRepository.save(PedidoItem.builder()
                            .pedido(pedido)
                            .dadosProduto(dadosProduto)
                            .quantidade(cItem.getQuantidade())
                            .precoUnitario(produto.getPreco())
                            .build());
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }
}

