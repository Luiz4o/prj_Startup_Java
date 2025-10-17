package com.startup.vanguard.service;

import com.startup.vanguard.dto.comprador.CompradorCreateDTO;
import com.startup.vanguard.dto.comprador.CompradorResponseDTO;
import com.startup.vanguard.dto.comprador.CompradorUpdateDTO;
import com.startup.vanguard.exception.ResourceNotFoundException;
import com.startup.vanguard.model.Comprador;
import com.startup.vanguard.model.Endereco;
import com.startup.vanguard.repository.CompradorRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class CompradorService {

    private final CompradorRepository compradorRepository;
    private final PasswordEncoder passwordEncoder;

    public CompradorService(CompradorRepository compradorRepository, PasswordEncoder passwordEncoder) {
        this.compradorRepository = compradorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public CompradorResponseDTO insertComprador(CompradorCreateDTO dto) {
        var password = passwordEncoder.encode(dto.password());
        Comprador comprador = new Comprador(dto,password);

        var compradorSaved = compradorRepository.save(comprador);
        return new CompradorResponseDTO(compradorSaved);
    }

    @Transactional
    public CompradorResponseDTO findById(Long id) {
        var comprador = compradorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comprador", id));
        return new CompradorResponseDTO(comprador);
    }

    @Transactional
    public List<CompradorResponseDTO> findAll() {
        return compradorRepository.findAll().stream()
                .map(CompradorResponseDTO::new)
                .toList();
    }

    @Transactional
    public CompradorResponseDTO update(Long id, CompradorUpdateDTO dto) {
        var comprador = compradorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comprador", id));

        comprador.setEmail(dto.email());
        comprador.setTelefone(dto.telefone());
        comprador.setNomeCompleto(dto.nomeCompleto());

        var compradorSaved = compradorRepository.save(comprador);
        return new CompradorResponseDTO(compradorSaved);
    }

    @Transactional
    public void delete(Long id) {
        var comprador = compradorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comprador", id));

        comprador.setAtivo(false);
        compradorRepository.save(comprador);
    }

    @Transactional
    public Endereco findEnderecoById(Long compradorId) {
        var comprador = compradorRepository.findById(compradorId)
                .orElseThrow(() -> new ResourceNotFoundException("Comprador", compradorId));
        return comprador.getEndereco();
    }

    @Transactional
    public Endereco updateEndereco(Long compradorId, Endereco novoEndereco) {
        var comprador = compradorRepository.findById(compradorId)
                .orElseThrow(() -> new ResourceNotFoundException("Comprador", compradorId));

        comprador.setEndereco(novoEndereco);
        var compradorAtualizado = compradorRepository.save(comprador);
        return compradorAtualizado.getEndereco();
    }
}
