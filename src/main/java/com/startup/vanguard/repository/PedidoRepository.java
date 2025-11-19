package com.startup.vanguard.repository;

import com.startup.vanguard.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {
    Optional<Pedido> findByIdPagamento(String idPagamento);
}
