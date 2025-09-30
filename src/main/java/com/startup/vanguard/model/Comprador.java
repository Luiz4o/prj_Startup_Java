package com.startup.vanguard.model;

import com.startup.vanguard.dto.comprador.CompradorCreateDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "compradores")
public class Comprador implements UserDetails {
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

    @Column(nullable = false)
    private String nomeCompleto;

    @Column(nullable = false, unique = true)
    private String documento;

    @Column(nullable = false)
    private String tipoPessoa;

    public Comprador(CompradorCreateDTO compradorCreateDTO, String password) {
        this.email = compradorCreateDTO.email();
        this.password = password;
        this.data_criacao = OffsetDateTime.now();
        this.telefone = compradorCreateDTO.telefone();
        this.isAtivo = true;
        this.nomeCompleto = compradorCreateDTO.nomeCompleto();
        this.documento = compradorCreateDTO.documento();
        this.tipoPessoa = compradorCreateDTO.tipoPessoa();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_COMPRADOR"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
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