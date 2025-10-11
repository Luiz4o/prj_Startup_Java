package com.startup.vanguard.service;

import com.startup.vanguard.dto.carrinho.CarrinhoRequestDTO;
import com.startup.vanguard.dto.carrinho.CarrinhoResponseDTO;
import com.startup.vanguard.dto.carrinho.CarrinhoUpdateDTO;
import com.startup.vanguard.exception.ResourceNotFoundException;
import com.startup.vanguard.model.Carrinho;
import com.startup.vanguard.repository.CarrinhoRepository;
import com.startup.vanguard.repository.CompradorRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final CompradorRepository compradorRepository;

    public CarrinhoService(CarrinhoRepository carrinhoRepository, CompradorRepository compradorRepository) {
        this.carrinhoRepository = carrinhoRepository;
        this.compradorRepository = compradorRepository;
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

    public CarrinhoResponseDTO insert(CarrinhoRequestDTO dto) {
        var comprador = compradorRepository.findById(dto.idComprador())
                .orElseThrow(() -> new ResourceNotFoundException("Comprador", dto.idComprador()));

        var carrinho = new Carrinho();
        carrinho.setComprador(comprador);
        carrinho.setDataCriacao(OffsetDateTime.now());
        carrinho.setStatus("ABERTO");

        var carrinhoSalvo = carrinhoRepository.save(carrinho);
        return new CarrinhoResponseDTO(carrinhoSalvo);
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
