package com.startup.vanguard.repository;

import com.startup.vanguard.dto.carrinho.EnumStatus;
import com.startup.vanguard.model.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarrinhoRepository extends JpaRepository<Carrinho,Long> {
    List<Carrinho> findByStatus(String status);

    Optional<Carrinho> findByUsuarioIdAndStatus(Long usuarioId, String status);
}
