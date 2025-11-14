package com.api._dejulio.service;

import com.api._dejulio.Entityes.Indicador;
import com.api._dejulio.repository.IndicadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IndicadorService {

    @Autowired
    private IndicadorRepository indicadorRepository;

    
    public List<Indicador> getAllIndicadores() {
        return indicadorRepository.findAll();
    }

    
    public Optional<Indicador> getIndicadorById(int id) {
        return indicadorRepository.findById(id);
    }

    
    public Indicador saveIndicador(Indicador indicador) {
        return indicadorRepository.save(indicador);
    }

   
    public void deleteIndicador(int id) {
        indicadorRepository.deleteById(id);
    }
    
    public List<Indicador> getIndicadoresBySeccion(int seccionId) {
        return indicadorRepository.findBySeccionId(seccionId);
    }
}

