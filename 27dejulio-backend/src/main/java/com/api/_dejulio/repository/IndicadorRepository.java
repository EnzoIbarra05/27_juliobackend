package com.api._dejulio.repository;

import com.api._dejulio.Entityes.Indicador;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IndicadorRepository extends JpaRepository<Indicador, Integer> {
    Optional<Indicador> findByNombre(String nombre);
   
 @Query("SELECT i FROM Indicador i WHERE i.seccion.id = :seccionId")
        List<Indicador> findBySeccionId(@Param("seccionId") int seccionId);

}
