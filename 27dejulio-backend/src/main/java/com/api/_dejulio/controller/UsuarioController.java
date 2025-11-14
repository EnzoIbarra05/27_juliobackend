package com.api._dejulio.controller;

import com.api._dejulio.Entityes.Usuario;
import com.api._dejulio.dto.UsuarioResponseDTO;
import com.api._dejulio.dto.usuarioDTO;
import com.api._dejulio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

   

@PostMapping("/login")
public ResponseEntity<UsuarioResponseDTO> login(@RequestBody usuarioDTO usuarioLogin) {
    String usuario = usuarioLogin.getUsuario();
    String contrasenia = usuarioLogin.getContrasenia();

    List<Usuario> usuarios = usuarioService.getAllUsuarios();

    for (Usuario u : usuarios) {
        if (u.getUsuario().equals(usuario) && u.getContrasenia().equals(contrasenia)) {
            // Crear DTO de respuesta sin contraseña
            UsuarioResponseDTO respuesta = new UsuarioResponseDTO(
                u.getId(),
                u.getNombre(),
                u.getApellido(),
                u.getUsuario()
            );
            return ResponseEntity.ok(respuesta);
        }
    }

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
}



    
    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioService.saveUsuario(usuario);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable int id, @RequestBody Usuario usuarioDetails) {
        return usuarioService.getUsuarioById(id).map(usuario -> {
            usuario.setNombre(usuarioDetails.getNombre());
            usuario.setApellido(usuarioDetails.getApellido());
            usuario.setUsuario(usuarioDetails.getUsuario());
            usuario.setContrasenia(usuarioDetails.getContrasenia());
            Usuario updated = usuarioService.saveUsuario(usuario);
            return ResponseEntity.ok(updated);
        }).orElse(ResponseEntity.notFound().build());
    }


   
}
