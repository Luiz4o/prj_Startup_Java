package com.startup.vanguard.service;

import com.startup.vanguard.dto.carrinho.EnumStatus;
import com.startup.vanguard.dto.carrinho.CarrinhoItemResquestDTO;
import com.startup.vanguard.dto.carrinho.CarrinhoRequestDTO;
import com.startup.vanguard.dto.carrinho.CarrinhoResponseDTO;
import com.startup.vanguard.dto.carrinho.CarrinhoUpdateDTO;
import com.startup.vanguard.exception.ResourceNotFoundException;
import com.startup.vanguard.model.Carrinho;
import com.startup.vanguard.model.CarrinhoItem;
import com.startup.vanguard.model.Produto;
import com.startup.vanguard.repository.CarrinhoItemRepository;
import com.startup.vanguard.repository.CarrinhoRepository;
import com.startup.vanguard.repository.UsuarioRepository;
import com.startup.vanguard.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CarrinhoItemRepository carrinhoItemRepository;
    private final ProdutoRepository produtoRepository;

    public CarrinhoService(CarrinhoRepository carrinhoRepository, UsuarioRepository usuarioRepository, CarrinhoItemRepository carrinhoItemRepository, ProdutoRepository produtoRepository) {
        this.carrinhoRepository = carrinhoRepository;
        this.usuarioRepository = usuarioRepository;
        this.carrinhoItemRepository = carrinhoItemRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<CarrinhoResponseDTO> findAll() {
        var carrinhos = carrinhoRepository.findByStatus(EnumStatus.COMPRANDO.name());
        return carrinhos.stream()
                .map(CarrinhoResponseDTO::new)
                .toList();
    }

    public CarrinhoResponseDTO findById(long id) {
        var carrinho = carrinhoRepository.findByUsuarioIdAndStatus(id,EnumStatus.COMPRANDO.name())
                .orElseThrow(() -> new ResourceNotFoundException("Carrinho", id));

        return new CarrinhoResponseDTO(carrinho);
    }

    public CarrinhoResponseDTO create(CarrinhoRequestDTO dto) {
        var comprador = usuarioRepository.findById(dto.idComprador())
                .orElseThrow(() -> new ResourceNotFoundException("Comprador", dto.idComprador()));

        var carrinho = new Carrinho();

        carrinho.setUsuario(comprador);
        carrinho.setDataCriacao(OffsetDateTime.now());
        carrinho.setStatus(EnumStatus.COMPRANDO.name());

        var carrinhoSalvo = carrinhoRepository.save(carrinho);
        return new CarrinhoResponseDTO(carrinhoSalvo);
    }

    public CarrinhoResponseDTO InsertItem(CarrinhoItemResquestDTO dto, Long idCarrinho){
        var carrinho = carrinhoRepository.findById(idCarrinho)
                .orElseThrow(() -> new ResourceNotFoundException("Carrinho", idCarrinho));

        var produto = produtoRepository.findById(dto.idProduto())
                .orElseThrow(() -> new ResourceNotFoundException("Produto", dto.idProduto()));

        var carrinhoItem = new CarrinhoItem(carrinho,produto, dto.quantidade());

        carrinho.addItem(carrinhoItem);
        carrinhoRepository.save(carrinho);

        return new CarrinhoResponseDTO(carrinho);
    }

    public CarrinhoResponseDTO update(long id, CarrinhoUpdateDTO dto) {
        var carrinho = carrinhoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Carrinho", id));

        carrinho.setStatus(dto.status().toString());
        carrinhoRepository.save(carrinho);

        return new CarrinhoResponseDTO(carrinho);
    }

    public void delete(long id) {
        if (!carrinhoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Carrinho", id);
        }
        carrinhoRepository.deleteById(id);
    }
}
