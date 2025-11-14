package com.api._dejulio.repository;

import com.api._dejulio.Entityes.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
     Optional<Usuario> findByApellido(String apellido);
}
