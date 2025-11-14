package com.api._dejulio.controller;

import com.api._dejulio.Entityes.Indicador;
import com.api._dejulio.Entityes.Seccion;
import com.api._dejulio.dto.IndicadorDTO;
import com.api._dejulio.service.IndicadorService;
import com.api._dejulio.service.SeccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/indicadores")
public class IndicadorController {

    @Autowired
    private IndicadorService indicadorService;

    @Autowired
    private SeccionService seccionService;

    // Obtener todos los indicadores
    @GetMapping
    public List<Indicador> getAllIndicadores() {
        return indicadorService.getAllIndicadores();
    }

    // Obtener indicador por id
    @GetMapping("/{id}")
    public ResponseEntity<Indicador> getIndicadorById(@PathVariable int id) {
        return indicadorService.getIndicadorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo indicador usando DTO (solo se necesita el id de la sección)
    @PostMapping
    public ResponseEntity<Indicador> crearIndicador(@RequestBody IndicadorDTO dto) {
        Seccion seccion = seccionService.getSeccionById(dto.getSeccionId())
                .orElseThrow(() -> new RuntimeException("Sección no encontrada"));

        Indicador indicador = new Indicador();
        indicador.setNombre(dto.getNombre());
        indicador.setCodigo(dto.getCodigo());
        indicador.setSeccion(seccion);

        Indicador guardado = indicadorService.saveIndicador(indicador);
        return ResponseEntity.ok(guardado);
    }

    // Actualizar un indicador existente
    @PutMapping("/{id}")
    public ResponseEntity<Indicador> updateIndicador(@PathVariable int id, @RequestBody IndicadorDTO dto) {
        return indicadorService.getIndicadorById(id).map(indicador -> {
            indicador.setNombre(dto.getNombre());
            indicador.setCodigo(dto.getCodigo());

            // Actualizamos la sección si se proporcionó un id
            if (dto.getSeccionId() > 0) {
                Seccion seccion = seccionService.getSeccionById(dto.getSeccionId())
                        .orElseThrow(() -> new RuntimeException("Sección no encontrada"));
                indicador.setSeccion(seccion);
            }

            Indicador updated = indicadorService.saveIndicador(indicador);
            return ResponseEntity.ok(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    // Eliminar un indicador
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIndicador(@PathVariable int id) {
        return indicadorService.getIndicadorById(id).map(indicador -> {
            indicadorService.deleteIndicador(id);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/seccion/{seccionId}")
    public ResponseEntity<List<Indicador>> getIndicadoresBySeccion(@PathVariable int seccionId) {
        List<Indicador> indicadores = indicadorService.getIndicadoresBySeccion(seccionId);
        if (indicadores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(indicadores);
    }
}
