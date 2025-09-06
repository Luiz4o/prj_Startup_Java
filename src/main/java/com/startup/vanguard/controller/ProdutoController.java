package com.startup.vanguard.controller;

import com.startup.vanguard.DTO.ProdutoCreateDTO;
import com.startup.vanguard.DTO.ProdutoResponseDTO;
import com.startup.vanguard.DTO.ProdutoUpdateDTO;
import com.startup.vanguard.model.Produto;
import com.startup.vanguard.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(name = "produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> getAllProdutos(){
        return  ResponseEntity.ok(produtoService.getAll());
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> insertProduto(@RequestBody ProdutoCreateDTO produtoCreateDTO){
        var produtoCreated = produtoService.insertProduto(produtoCreateDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(produtoCreated.id()) // ID retornado do produto salvo
                .toUri();

        return ResponseEntity.created(uri).body(produtoCreated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> getById(@PathVariable Long id){
        var produto = produtoService.getById(id);

        return ResponseEntity.ok(produto);
    }


}
