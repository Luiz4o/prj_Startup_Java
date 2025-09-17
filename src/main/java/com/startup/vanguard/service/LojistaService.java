package com.startup.vanguard.service;

import com.startup.vanguard.DTO.lojista.LojistaCreateDTO;
import com.startup.vanguard.DTO.lojista.LojistaResponseDTO;
import com.startup.vanguard.model.Lojista;
import com.startup.vanguard.repository.LojistaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LojistaService {

    private final LojistaRepository lojistaRepository;

    private final PasswordEncoder passwordEncoder;

    public LojistaService(LojistaRepository lojistaRepository, PasswordEncoder passwordEncoder) {
        this.lojistaRepository = lojistaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public LojistaResponseDTO insertLojista(LojistaCreateDTO lojistaCreateDTO){
        Lojista lojista = new Lojista(lojistaCreateDTO, passwordEncoder.encode(lojistaCreateDTO.password()));

        var lojistaSaved =lojistaRepository.save(lojista);

        return new LojistaResponseDTO(lojistaSaved);

    }
}
