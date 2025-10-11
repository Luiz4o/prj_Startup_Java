package com.startup.vanguard.repository;

import com.startup.vanguard.model.PedidoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoItemRepository extends JpaRepository<PedidoItem,Long> {
}
