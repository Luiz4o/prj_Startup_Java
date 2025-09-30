package com.startup.vanguard.controller;

import com.startup.vanguard.dto.comprador.CompradorCreateDTO;
import com.startup.vanguard.dto.comprador.CompradorResponseDTO;
import com.startup.vanguard.dto.comprador.CompradorUpdateDTO;
import com.startup.vanguard.service.CompradorService;
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
@RequestMapping("/api/comprador")
public class CompradorController {

    private final CompradorService compradorService;

    public CompradorController(CompradorService compradorService) {
        this.compradorService = compradorService;
    }

    @PostMapping("/create")
    public ResponseEntity<CompradorResponseDTO> criarComprador(@RequestBody CompradorCreateDTO dto) {
        var compradorSaved = compradorService.insertComprador(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(compradorSaved.id())
                .toUri();

        return ResponseEntity.created(uri).body(compradorSaved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompradorResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(compradorService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<CompradorResponseDTO>> findAll() {
        return ResponseEntity.ok(compradorService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompradorResponseDTO> update(@PathVariable Long id,
                                                       @RequestBody CompradorUpdateDTO dto) {
        var compradorAtualizado = compradorService.update(id, dto);
        return ResponseEntity.ok(compradorAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        compradorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

