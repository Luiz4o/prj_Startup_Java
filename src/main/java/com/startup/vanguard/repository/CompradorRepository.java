package com.startup.vanguard.repository;

import com.startup.vanguard.model.Comprador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompradorRepository extends JpaRepository<Comprador,Long> {
    Optional<Comprador> findByEmail(String email);
}
