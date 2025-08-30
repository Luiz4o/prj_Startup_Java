package com.startup.vanguard.service;

import com.startup.vanguard.DTO.ProdutoCreateDTO;
import com.startup.vanguard.DTO.ProdutoResponseDTO;
import com.startup.vanguard.exception.ResourceNotFoundException;
import com.startup.vanguard.model.Produto;
import com.startup.vanguard.repository.CategoriaRepository;
import com.startup.vanguard.repository.LojistaRepository;
import com.startup.vanguard.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final LojistaRepository lojistaRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository, LojistaRepository lojistaRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.lojistaRepository = lojistaRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<ProdutoResponseDTO> getAll(){
        return produtoRepository.findAll().stream()
                .map(p -> new ProdutoResponseDTO(p))
                .toList();
    }

    public ProdutoResponseDTO insertProduto(ProdutoCreateDTO produtoCreateDTO) {
        var lojista = lojistaRepository.findById(produtoCreateDTO.id_lojista())
                .orElseThrow(() -> new ResourceNotFoundException("Lojista", produtoCreateDTO.id_lojista()));

        var categoria = categoriaRepository.findById(produtoCreateDTO.id_categoria())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria", produtoCreateDTO.id_categoria()));

        var produtoCreated =produtoRepository.save(new Produto(lojista,categoria,produtoCreateDTO));

        return new ProdutoResponseDTO(produtoCreated);
    }
}
