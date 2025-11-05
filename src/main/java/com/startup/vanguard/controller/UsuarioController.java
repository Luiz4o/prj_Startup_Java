package com.startup.vanguard.controller;

import com.startup.vanguard.dto.usuario.UsuarioCreateDTO;
import com.startup.vanguard.dto.usuario.UsuarioResponseDTO;
import com.startup.vanguard.dto.usuario.UsuarioUpdateDTO;
import com.startup.vanguard.model.Endereco;
import com.startup.vanguard.service.UsuarioService;
import jakarta.validation.Valid;
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
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/create")
    public ResponseEntity<UsuarioResponseDTO> criarUsuario(@RequestBody @Valid UsuarioCreateDTO dto) {

        System.out.println(dto);
        var usuarioSaved = usuarioService.insertUsuario(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(usuarioSaved.id())
                .toUri();

        return ResponseEntity.created(uri).body(usuarioSaved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> findAll() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> update(@PathVariable Long id,
                                                     @RequestBody UsuarioUpdateDTO dto) {
        var usuarioAtualizado = usuarioService.update(id, dto);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{usuarioId}/endereco")
    public ResponseEntity<Endereco> findEnderecoById(@PathVariable Long usuarioId) {
        var endereco = usuarioService.findEnderecoById(usuarioId);
        return ResponseEntity.ok(endereco);
    }

    @PutMapping("/{usuarioId}/endereco")
    public ResponseEntity<Endereco> updateEndereco(@PathVariable Long usuarioId,
                                                   @RequestBody Endereco novoEndereco) {
        var enderecoAtualizado = usuarioService.updateEndereco(usuarioId, novoEndereco);
        return ResponseEntity.ok(enderecoAtualizado);
    }
}

