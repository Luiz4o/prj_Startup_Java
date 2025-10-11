package com.startup.vanguard.service;

import com.startup.vanguard.dto.pedido.PedidoRequestDTO;
import com.startup.vanguard.dto.pedido.PedidoResponseDTO;
import com.startup.vanguard.exception.ResourceNotFoundException;
import com.startup.vanguard.model.Pedido;
import com.startup.vanguard.repository.CarrinhoRepository;
import com.startup.vanguard.repository.CompradorRepository;
import com.startup.vanguard.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final CarrinhoRepository carrinhoRepository;
    private final CompradorRepository compradorRepository;
    private final PedidoItemService pedidoItemService;

    public PedidoService(PedidoRepository pedidoRepository, CarrinhoRepository carrinhoRepository, CompradorRepository compradorRepository, PedidoItemService pedidoItemService) {
        this.pedidoRepository = pedidoRepository;
        this.carrinhoRepository = carrinhoRepository;
        this.compradorRepository = compradorRepository;
        this.pedidoItemService = pedidoItemService;
    }

    public List<PedidoResponseDTO> getAll(){
        return pedidoRepository.findAll().stream()
                .map(p -> PedidoResponseDTO.builder()
                        .id(p.getId())
                        .dataPedido(p.getDataPedido())
                        .statusPedido(p.getStatusPedido())
                        .carrinho(p.getCarrinho())
                        .enderecoEntrega(p.getEnderecoEntrega())
                        .ultimaAtualizacao(p.getUltimaAtualizacao())
                        .valor_total(p.getValor_total())
                        .comprador(p.getComprador())
                        .build())
                .toList();
    }

    public PedidoResponseDTO getByID(long id){
        return pedidoRepository.findById(id)
                .map(p -> PedidoResponseDTO.builder()
                        .id(p.getId())
                        .dataPedido(p.getDataPedido())
                        .statusPedido(p.getStatusPedido())
                        .carrinho(p.getCarrinho())
                        .enderecoEntrega(p.getEnderecoEntrega())
                        .ultimaAtualizacao(p.getUltimaAtualizacao())
                        .valor_total(p.getValor_total())
                        .comprador(p.getComprador())
                        .build())
                .orElseThrow(() -> new ResourceNotFoundException("Pedido", id));
    }

    @Transactional
    public PedidoResponseDTO insertPedido(PedidoRequestDTO pedidoRequestDTO){
        var carrinhoPedido = carrinhoRepository.findById(pedidoRequestDTO.idCarrinho())
                        .orElseThrow(() -> new ResourceNotFoundException("Carrinho", pedidoRequestDTO.idCarrinho()));



        var compradorPedido = compradorRepository.findById(pedidoRequestDTO.idComprador())
                        .orElseThrow(() -> new ResourceNotFoundException("Comprador", pedidoRequestDTO.idComprador()));
        var pedido = pedidoRepository.save(Pedido.builder()
                .carrinho(carrinhoPedido)
                .dataPedido(pedidoRequestDTO.dataPedido())
                .statusPedido(pedidoRequestDTO.statusPedido())
                .valor_total(pedidoRequestDTO.valor_total())
                .enderecoEntrega(pedidoRequestDTO.enderecoEntrega())
                .ultimaAtualizacao(pedidoRequestDTO.ultimaAtualizacao())
                .comprador(compradorPedido)
                .build());

        // ADICIONAR OS ITENS DO PEDIDO APOS CRIAR O PEDIDO VOU EPGAR A LISTA DE ITENS CARRINHO PARA CRIAR O PEDIDO ITENS E INSERIR NO PEDIDO(UPDATE?)
        pedidoItemService.createPedidoItemByCarrinhoItem(carrinhoPedido.getItens(),pedido);

        // ATUALIZAR O ITEM PARA ADICIONAR OS ITENS CRIADO A PARTIR DO CARRINHO ITEM
        pedido = pedidoRepository.save();

        return PedidoResponseDTO.builder()
                .id(pedido.getId())
                .dataPedido(pedido.getDataPedido())
                .statusPedido(pedido.getStatusPedido())
                .carrinho(pedido.getCarrinho())
                .enderecoEntrega(pedido.getEnderecoEntrega())
                .ultimaAtualizacao(pedido.getUltimaAtualizacao())
                .valor_total(pedido.getValor_total())
                .comprador(pedido.getComprador())
                .build();
    }

    @Transactional
    public void deletePedido(Long id){
        var pedido = pedidoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pedido", id));
        pedidoRepository.delete(pedido);
    }
}
