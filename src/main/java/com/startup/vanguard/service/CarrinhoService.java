package com.startup.vanguard.service;

import com.startup.vanguard.dto.EnumStatus;
import com.startup.vanguard.dto.carrinho.CarrinhoItemResquestDTO;
import com.startup.vanguard.dto.carrinho.CarrinhoRequestDTO;
import com.startup.vanguard.dto.carrinho.CarrinhoResponseDTO;
import com.startup.vanguard.dto.carrinho.CarrinhoUpdateDTO;
import com.startup.vanguard.exception.ResourceNotFoundException;
import com.startup.vanguard.model.Carrinho;
import com.startup.vanguard.model.CarrinhoItem;
import com.startup.vanguard.repository.CarrinhoItemRepository;
import com.startup.vanguard.repository.CarrinhoRepository;
import com.startup.vanguard.repository.CompradorRepository;
import com.startup.vanguard.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final CompradorRepository compradorRepository;
    private final CarrinhoItemRepository carrinhoItemRepository;
    private final ProdutoRepository produtoRepository;

    public CarrinhoService(CarrinhoRepository carrinhoRepository, CompradorRepository compradorRepository, CarrinhoItemRepository carrinhoItemRepository, ProdutoRepository produtoRepository) {
        this.carrinhoRepository = carrinhoRepository;
        this.compradorRepository = compradorRepository;
        this.carrinhoItemRepository = carrinhoItemRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<CarrinhoResponseDTO> findAll() {
        var carrinhos = carrinhoRepository.findAll();
        return carrinhos.stream().map(CarrinhoResponseDTO::new).toList();
    }

    public CarrinhoResponseDTO findById(long id) {
        var carrinho = carrinhoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Carrinho", id));
        return new CarrinhoResponseDTO(carrinho);
    }

    public CarrinhoResponseDTO create(CarrinhoRequestDTO dto) {
        var comprador = compradorRepository.findById(dto.idComprador())
                .orElseThrow(() -> new ResourceNotFoundException("Comprador", dto.idComprador()));

        var carrinho = new Carrinho();

        carrinho.setComprador(comprador);
        carrinho.setDataCriacao(OffsetDateTime.now());
        carrinho.setStatus(EnumStatus.COMPRANDO);

        var carrinhoSalvo = carrinhoRepository.save(carrinho);
        return new CarrinhoResponseDTO(carrinhoSalvo);
    }

    public CarrinhoResponseDTO InsertItem(CarrinhoItemResquestDTO dto){
        var carrinho = carrinhoRepository.findById(dto.idCarrinho())
                .orElseThrow(() -> new ResourceNotFoundException("Carrinho", dto.idCarrinho()));

        var produto = produtoRepository.findById(dto.idCarrinho())
                .orElseThrow(() -> new ResourceNotFoundException("Produto", dto.idProduto()));

        var carrinhoItem = new CarrinhoItem(carrinho,produto, dto.quantidade());

        carrinho.addItem(carrinhoItem);
        carrinhoRepository.save(carrinho);

        return new CarrinhoResponseDTO(carrinho);
    }

    public CarrinhoResponseDTO update(long id, CarrinhoUpdateDTO dto) {
        var carrinho = carrinhoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Carrinho", id));

        carrinho.setStatus(dto.status());
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
