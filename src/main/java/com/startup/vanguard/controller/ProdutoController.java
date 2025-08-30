package com.startup.vanguard.controller;

import com.startup.vanguard.DTO.ProdutoCreateDTO;
import com.startup.vanguard.DTO.ProdutoResponseDTO;
import com.startup.vanguard.model.Produto;
import com.startup.vanguard.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(name = "produto")
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
    }
}
