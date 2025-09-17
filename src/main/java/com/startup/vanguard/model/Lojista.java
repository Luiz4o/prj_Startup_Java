package com.startup.vanguard.model;

import com.startup.vanguard.DTO.lojista.LojistaCreateDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.OffsetDateTime;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "lojistas")
public class Lojista implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private OffsetDateTime data_criacao;

    @Column(nullable = false)
    private String telefone;

    @Column
    private boolean isAtivo;

    @Column(nullable = false, unique = true)
    private String razaoSocial;

    @Column(nullable = false, unique = true)
    private String CNPJ;

    public Lojista() {
    }

    public Lojista(LojistaCreateDTO lojistaCreateDTO, String password) {
        this.email = lojistaCreateDTO.email();
        this.password = password;
        this.data_criacao = OffsetDateTime.now();
        this.telefone = lojistaCreateDTO.telefone();
        this.isAtivo = true;
        this.razaoSocial = lojistaCreateDTO.razaoSocial();
        this.CNPJ = lojistaCreateDTO.cnpj();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_LOJISTA"));
    }

    @Override
    public String getPassword() {
        return this.getPassword();
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isAtivo();
    }
}