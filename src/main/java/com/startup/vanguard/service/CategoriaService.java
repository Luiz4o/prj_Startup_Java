package com.startup.vanguard.service;

import com.startup.vanguard.dto.categoria.CategoriaReponseDTO;
import com.startup.vanguard.dto.categoria.CategoriaRequestDTO;
import com.startup.vanguard.dto.categoria.CategoriaUpdateDTO;
import com.startup.vanguard.exception.ResourceNotFoundException;
import com.startup.vanguard.model.Categoria;
import com.startup.vanguard.repository.CategoriaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaReponseDTO> findAll(){
        var categorias = categoriaRepository.findAll();

        return categorias.stream().map(CategoriaReponseDTO::new).toList();
    }

    public CategoriaReponseDTO findById(long id){
        var categoria = categoriaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Categoria",id));
        return new CategoriaReponseDTO(categoria);
    }

    public CategoriaReponseDTO insert(CategoriaRequestDTO categoriaRequestDTO){
        var categoria = categoriaRepository.save(new Categoria(categoriaRequestDTO));

        return new CategoriaReponseDTO(categoria);
    }

    public CategoriaReponseDTO update(long id, CategoriaUpdateDTO dto){
        var categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria", id));

        // Atualiza os campos
        categoria.setNome(dto.nome());
        categoria.setDescricao(dto.descricao());

        categoriaRepository.save(categoria);
        return new CategoriaReponseDTO(categoria);
    }

    public void delete(long id){
        var categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria", id));
        categoriaRepository.delete(categoria);
    }
}
