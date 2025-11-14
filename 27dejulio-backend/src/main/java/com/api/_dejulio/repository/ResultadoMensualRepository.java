package com.api._dejulio.repository;

import com.api._dejulio.Entityes.resultadoMensual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultadoMensualRepository extends JpaRepository<resultadoMensual, Integer> {
    
}

