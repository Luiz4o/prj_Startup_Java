package com.startup.vanguard.service;

import com.startup.vanguard.dto.lojista.LojistaCreateDTO;
import com.startup.vanguard.dto.lojista.LojistaResponseDTO;
import com.startup.vanguard.dto.lojista.LojistaUpdateDTO;
import com.startup.vanguard.exception.ResourceNotFoundException;
import com.startup.vanguard.model.Lojista;
import com.startup.vanguard.repository.LojistaRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LojistaService {

    private final LojistaRepository lojistaRepository;

    private final PasswordEncoder passwordEncoder;

    public LojistaService(LojistaRepository lojistaRepository, PasswordEncoder passwordEncoder) {
        this.lojistaRepository = lojistaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public LojistaResponseDTO insertLojista(LojistaCreateDTO lojistaCreateDTO){
        Lojista lojista = new Lojista(lojistaCreateDTO, passwordEncoder.encode(lojistaCreateDTO.password()));

        var lojistaSaved =lojistaRepository.save(lojista);

        return new LojistaResponseDTO(lojistaSaved);

    }

    @Transactional
    public LojistaResponseDTO findById(Long id) {
        var lojista = lojistaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lojista", id));
        return new LojistaResponseDTO(lojista);
    }

    @Transactional
    public List<LojistaResponseDTO> findAll() {
        return lojistaRepository.findAll().stream()
                .map(LojistaResponseDTO::new)
                .toList();
    }

    @Transactional
    public LojistaResponseDTO update(Long id, LojistaUpdateDTO dto) {
        var lojista = lojistaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lojista", id));

        lojista.setEmail(dto.email());
        lojista.setTelefone(dto.telefone());
        lojista.setRazaoSocial(dto.razaoSocial());

        var lojistaSaved = lojistaRepository.save(lojista);
        return new LojistaResponseDTO(lojistaSaved);
    }

    @Transactional
    public void delete(Long id) {
        var lojista = lojistaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lojista", id));

        lojista.setAtivo(false);
        lojistaRepository.save(lojista);
    }
}
