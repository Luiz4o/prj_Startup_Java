package com.startup.vanguard.repository;

import com.startup.vanguard.dto.carrinho.EnumStatus;
import com.startup.vanguard.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
}
