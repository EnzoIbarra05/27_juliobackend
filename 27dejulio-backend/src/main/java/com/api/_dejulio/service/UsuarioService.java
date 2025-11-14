package com.api._dejulio.service;

import com.api._dejulio.Entityes.Usuario;
import com.api._dejulio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

   
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    
    public Optional<Usuario> getUsuarioById(int id) {
        return usuarioRepository.findById(id);
    }

    
    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    
    public void deleteUsuario(int id) {
        usuarioRepository.deleteById(id);
    }
}
