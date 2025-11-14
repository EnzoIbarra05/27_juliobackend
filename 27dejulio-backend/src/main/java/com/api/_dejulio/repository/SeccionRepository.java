

package com.api._dejulio.repository;

import com.api._dejulio.Entityes.Seccion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeccionRepository extends JpaRepository<Seccion, Integer> {
   Optional<Seccion> findByNombre(String nombre);
}
