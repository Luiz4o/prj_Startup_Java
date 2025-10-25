package com.startup.vanguard.service;

import com.startup.vanguard.dto.usuario.UsuarioCreateDTO;
import com.startup.vanguard.dto.usuario.UsuarioResponseDTO;
import com.startup.vanguard.dto.usuario.UsuarioUpdateDTO;
import com.startup.vanguard.exception.ResourceNotFoundException;
import com.startup.vanguard.model.Usuario;
import com.startup.vanguard.model.Endereco;
import com.startup.vanguard.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UsuarioResponseDTO insertUsuario(UsuarioCreateDTO dto) {
        var password = passwordEncoder.encode(dto.password());
        Usuario usuario = new Usuario(dto,password);

        var usuarioSaved = usuarioRepository.save(usuario);
        return new UsuarioResponseDTO(usuarioSaved);
    }

    @Transactional
    public UsuarioResponseDTO findById(Long id) {
        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário", id));
        return new UsuarioResponseDTO(usuario);
    }

    @Transactional
    public List<UsuarioResponseDTO> findAll() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioResponseDTO::new)
                .toList();
    }

    @Transactional
    public UsuarioResponseDTO update(Long id, UsuarioUpdateDTO dto) {
        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário", id));

        usuario.setEmail(dto.email());
        usuario.setTelefone(dto.telefone());
        usuario.setNomeCompleto(dto.nomeCompleto());

        var usuarioSaved = usuarioRepository.save(usuario);
        return new UsuarioResponseDTO(usuarioSaved);
    }

    @Transactional
    public void delete(Long id) {
        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário", id));

        usuario.setAtivo(false);
        usuarioRepository.save(usuario);
    }

    @Transactional
    public Endereco findEnderecoById(Long usuarioId) {
        var usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário", usuarioId));
        return usuario.getEndereco();
    }

    @Transactional
    public Endereco updateEndereco(Long usuarioId, Endereco novoEndereco) {
        var usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário", usuarioId));

        usuario.setEndereco(novoEndereco);
        var usuarioAtualizado = usuarioRepository.save(usuario);
        return usuarioAtualizado.getEndereco();
    }
}
