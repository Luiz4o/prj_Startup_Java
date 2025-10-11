package com.startup.vanguard.repository;

import com.startup.vanguard.model.CarrinhoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrinhoItemRepository extends JpaRepository<CarrinhoItem,Long> {
}
