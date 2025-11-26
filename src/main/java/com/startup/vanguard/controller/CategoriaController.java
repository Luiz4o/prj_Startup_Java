package com.startup.vanguard.controller;

import com.startup.vanguard.dto.categoria.CategoriaReponseDTO;
import com.startup.vanguard.dto.categoria.CategoriaRequestDTO;
import com.startup.vanguard.dto.categoria.CategoriaUpdateDTO;
import com.startup.vanguard.model.Categoria;
import com.startup.vanguard.repository.CategoriaRepository;
import com.startup.vanguard.service.CategoriaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping("/api/categoria")
@SecurityRequirement(name = "Bearer Authentication")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaReponseDTO>> findAll(){
        return ResponseEntity.ok(categoriaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaReponseDTO> findById(@PathVariable long id){
        return ResponseEntity.ok(categoriaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CategoriaReponseDTO> insertCategoria(@RequestBody CategoriaRequestDTO categoriaRequestDTO){
        var categoria = categoriaService.insert(categoriaRequestDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoria.id())
                .toUri();

        return ResponseEntity.created(uri).body(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaReponseDTO> updateCategoria(@PathVariable long id,
                                                               @RequestBody CategoriaUpdateDTO categoriaUpdateDTO){
        var categoriaAtualizada = categoriaService.update(id, categoriaUpdateDTO);
        return ResponseEntity.ok(categoriaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable long id){
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
