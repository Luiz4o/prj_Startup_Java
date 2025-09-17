package com.startup.vanguard.repository;

import com.startup.vanguard.model.Lojista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LojistaRepository extends JpaRepository<Lojista,Long> {
    Optional<Lojista> findByEmail(String email);
}
