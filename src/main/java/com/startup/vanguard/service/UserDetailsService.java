package com.startup.vanguard.service;

import com.startup.vanguard.model.Comprador;
import com.startup.vanguard.model.Lojista;
import com.startup.vanguard.repository.CompradorRepository;
import com.startup.vanguard.repository.LojistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsService {

    @Autowired
    private LojistaRepository lojistaRepository;

    @Autowired
    private CompradorRepository compradorRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Lojista> lojista = lojistaRepository.findByEmail(username);
        if (lojista.isPresent()) {
            return lojista.get();
        }

        Optional<Comprador> comprador = compradorRepository.findByEmail(username);
        if (comprador.isPresent()) {
            return comprador.get();
        }

        throw new UsernameNotFoundException("Usuário não encontrado: " + username);
    }
}