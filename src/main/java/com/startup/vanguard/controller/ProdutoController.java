package com.startup.vanguard.controller;

import com.startup.vanguard.dto.produto.ProdutoCreateDTO;
import com.startup.vanguard.dto.produto.ProdutoResponseDTO;
import com.startup.vanguard.dto.produto.ProdutoUpdateDTO;
import com.startup.vanguard.service.ProdutoService;
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
@RequestMapping("/api/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> findAll(){
        return  ResponseEntity.ok(produtoService.getAll());
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> insertProduto(@RequestBody ProdutoCreateDTO produtoCreateDTO){
        var produtoCreated = produtoService.insertProduto(produtoCreateDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(produtoCreated.id())
                .toUri();

        return ResponseEntity.created(uri).body(produtoCreated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> getById(@PathVariable Long id){
        var produto = produtoService.getById(id);

        return ResponseEntity.ok(produto);
    }

    @PutMapping()
    public ResponseEntity<ProdutoResponseDTO> updateProduto(@RequestBody ProdutoUpdateDTO produtoUpdateDTO){
        return ResponseEntity.ok().body(produtoService.updateProduto(produtoUpdateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProdutuo(@PathVariable Long id){
        produtoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
