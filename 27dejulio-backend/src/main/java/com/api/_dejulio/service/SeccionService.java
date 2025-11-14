 package com.api._dejulio.service;

import com.api._dejulio.Entityes.Seccion;
import com.api._dejulio.repository.SeccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeccionService {

    @Autowired
    private SeccionRepository seccionRepository;

    
    public List<Seccion> getAllSecciones() {
        return seccionRepository.findAll();
    }

    
    public Optional<Seccion> getSeccionById(int id) {
        return seccionRepository.findById(id);
    }

    
    public Seccion saveSeccion(Seccion seccion) {
        return seccionRepository.save(seccion);
    }

    
    public void deleteSeccion(int id) {
        seccionRepository.deleteById(id);
    }
}
