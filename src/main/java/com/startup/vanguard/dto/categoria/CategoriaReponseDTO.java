package com.startup.vanguard.dto.categoria;

import com.startup.vanguard.model.Categoria;

public record CategoriaReponseDTO(
        long id,
        String nome,
        String descricao
) {
    public CategoriaReponseDTO (Categoria c){
        this(c.getId(), c.getNome(), c.getDescricao());
    }
}
