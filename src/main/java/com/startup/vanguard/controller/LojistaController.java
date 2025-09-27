package com.startup.vanguard.controller;

import com.startup.vanguard.dto.lojista.LojistaCreateDTO;
import com.startup.vanguard.dto.lojista.LojistaResponseDTO;
import com.startup.vanguard.dto.lojista.LojistaUpdateDTO;
import com.startup.vanguard.service.LojistaService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/lojista")
public class LojistaController {

    private final LojistaService lojistaService;

    public LojistaController(LojistaService lojistaService) {
        this.lojistaService = lojistaService;
    }

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

    @GetMapping("/{id}")
    public ResponseEntity<LojistaResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(lojistaService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<LojistaResponseDTO>> findAll() {
        return ResponseEntity.ok(lojistaService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<LojistaResponseDTO> update(@PathVariable Long id,
                                                     @RequestBody LojistaUpdateDTO dto) {
        var lojistaAtualizado = lojistaService.update(id, dto);
        return ResponseEntity.ok(lojistaAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        lojistaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
