package com.startup.vanguard.repository;

import com.startup.vanguard.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {

    List<Produto> findByNomeContainingIgnoreCase(String parteNome);

}
