package com.startup.vanguard.controller;

import com.startup.vanguard.dto.lojista.LojistaCreateDTO;
import com.startup.vanguard.dto.lojista.LojistaResponseDTO;
import com.startup.vanguard.service.LojistaService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("/api/lojista")
public class LojistaController {

    private final LojistaService lojistaService;

    public LojistaController(LojistaService lojistaService) {
        this.lojistaService = lojistaService;
    }

    @Transactional
    @PostMapping("/create")
    public ResponseEntity<LojistaResponseDTO> criarLojista(@RequestBody LojistaCreateDTO dto) {
        System.out.println(dto);

        var lojistaSaved = lojistaService.insertLojista(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(lojistaSaved.id())
                .toUri();

        return ResponseEntity.created(uri).body(lojistaSaved);
    }
}
