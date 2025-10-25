package com.startup.vanguard.service;

import com.startup.vanguard.dto.pedido.PedidoRequestDTO;
import com.startup.vanguard.dto.pedido.PedidoResponseDTO;
import com.startup.vanguard.dto.usuario.UsuarioResponseDTO;
import com.startup.vanguard.exception.ResourceNotFoundException;
import com.startup.vanguard.model.Pedido;
import com.startup.vanguard.repository.CarrinhoRepository;
import com.startup.vanguard.repository.UsuarioRepository;
import com.startup.vanguard.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final CarrinhoRepository carrinhoRepository;
    private final UsuarioRepository compradorRepository;
    private final PedidoItemService pedidoItemService;

    public PedidoService(PedidoRepository pedidoRepository, CarrinhoRepository carrinhoRepository, UsuarioRepository compradorRepository, PedidoItemService pedidoItemService) {
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
                        .enderecoEntrega(p.getEnderecoEntrega())
                        .ultimaAtualizacao(p.getUltimaAtualizacao())
                        .valor_total(p.getValor_total())
                        .comprador(new UsuarioResponseDTO(p.getUsuario()))
                        .build())
                .toList();
    }

    public PedidoResponseDTO getByID(long id){
        return pedidoRepository.findById(id)
                .map(p -> PedidoResponseDTO.builder()
                        .id(p.getId())
                        .dataPedido(p.getDataPedido())
                        .statusPedido(p.getStatusPedido())
                        .enderecoEntrega(p.getEnderecoEntrega())
                        .ultimaAtualizacao(p.getUltimaAtualizacao())
                        .valor_total(p.getValor_total())
                        .comprador(new UsuarioResponseDTO(p.getUsuario()))
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
                .dataPedido(OffsetDateTime.now())
                .statusPedido("PROCESSANDO")
                .enderecoEntrega(pedidoRequestDTO.enderecoEntrega())
                .ultimaAtualizacao(OffsetDateTime.now())
                .usuario(compradorPedido)
                .build());

        var pedidosItem = pedidoItemService.createPedidoItemByCarrinhoItem(carrinhoPedido.getItens(),pedido);

        var valorTotal = pedidosItem.stream()
                .map(item -> item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        pedido.setValor_total(valorTotal);
        pedido.setPedidoItems(pedidosItem);
        pedido = pedidoRepository.save(pedido);

        return PedidoResponseDTO.builder()
                .id(pedido.getId())
                .dataPedido(pedido.getDataPedido())
                .statusPedido(pedido.getStatusPedido())
                .enderecoEntrega(pedido.getEnderecoEntrega())
                .ultimaAtualizacao(pedido.getUltimaAtualizacao())
                .valor_total(pedido.getValor_total())
                .comprador(new UsuarioResponseDTO(pedido.getUsuario()))
                .itens(pedido.getPedidoItems())
                .build();
    }

    @Transactional
    public void deletePedido(Long id){
        var pedido = pedidoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pedido", id));
        pedidoRepository.delete(pedido);
    }
}
