package com.startup.vanguard.service;

import com.startup.vanguard.dto.produto.ProdutoCreateDTO;
import com.startup.vanguard.dto.produto.ProdutoResponseDTO;
import com.startup.vanguard.dto.produto.ProdutoUpdateDTO;
import com.startup.vanguard.exception.ResourceNotFoundException;
import com.startup.vanguard.model.Produto;
import com.startup.vanguard.repository.CategoriaRepository;
import com.startup.vanguard.repository.LojistaRepository;
import com.startup.vanguard.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
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
                .map(ProdutoResponseDTO::new)
                .toList();
    }

    @Transactional
    public ProdutoResponseDTO insertProduto(ProdutoCreateDTO produtoCreateDTO) {
        var lojista = lojistaRepository.findById(produtoCreateDTO.id_lojista())
                .orElseThrow(() -> new ResourceNotFoundException("Lojista", produtoCreateDTO.id_lojista()));

        var categoria = categoriaRepository.findById(produtoCreateDTO.id_categoria())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria", produtoCreateDTO.id_categoria()));

        var produtoCreated =produtoRepository.save(new Produto(lojista,categoria,produtoCreateDTO));

        return new ProdutoResponseDTO(produtoCreated);
    }

    @Transactional
    public ProdutoResponseDTO updateProduto(ProdutoUpdateDTO produtoUpdateDTO){
        var produto = produtoRepository.findById(produtoUpdateDTO.id())
                .orElseThrow(() -> new ResourceNotFoundException("Produto",produtoUpdateDTO.id()));

        if (produtoUpdateDTO.id_categoria() != null) {
            var categoria = categoriaRepository.findById(produtoUpdateDTO.id_categoria())
                    .orElseThrow(() -> new ResourceNotFoundException("Categoria",produtoUpdateDTO.id_categoria()));
            produto.setCategoria(categoria);
        }

        if (produtoUpdateDTO.nome() != null && !produtoUpdateDTO.nome().isBlank()) {
            produto.setNome(produtoUpdateDTO.nome());
        }

        if (produtoUpdateDTO.descricao() != null && !produtoUpdateDTO.descricao().isBlank()) {
            produto.setDescricao(produtoUpdateDTO.descricao());
        }

        if (produtoUpdateDTO.price() != null) {
            produto.setPrice(produtoUpdateDTO.price());
        }

        if (produtoUpdateDTO.quantidadeEstoque() != null) {
            produto.setQuantidadeEstoque(produtoUpdateDTO.quantidadeEstoque());
        }

        return new ProdutoResponseDTO(produtoRepository.save(produto));
    }

    public ProdutoResponseDTO getById(Long id) {
        var produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto",id));

        return new ProdutoResponseDTO(produto);
    }
}
