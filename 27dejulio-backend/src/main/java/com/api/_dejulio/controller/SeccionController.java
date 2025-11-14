package com.api._dejulio.controller;

import com.api._dejulio.Entityes.Seccion;
import com.api._dejulio.service.SeccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/secciones")
public class SeccionController {

    @Autowired
    private SeccionService seccionService;

    // Obtener todas las secciones
    @GetMapping
    public List<Seccion> getAllSecciones() {
        return seccionService.getAllSecciones();
    }

    // Obtener seccion por id
    @GetMapping("/{id}")
    public ResponseEntity<Seccion> getSeccionById(@PathVariable int id) {
        return seccionService.getSeccionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear una nueva seccion
    @PostMapping
    public Seccion createSeccion(@RequestBody Seccion seccion) {
        return seccionService.saveSeccion(seccion);
    }

    // Actualizar una seccion existente
    @PutMapping("/{id}")
    public ResponseEntity<Seccion> updateSeccion(@PathVariable int id, @RequestBody Seccion seccionDetails) {
        return seccionService.getSeccionById(id).map(seccion -> {
            seccion.setNombre(seccionDetails.getNombre());
            Seccion updated = seccionService.saveSeccion(seccion);
            return ResponseEntity.ok(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    // Eliminar una seccion
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeccion(@PathVariable int id) {
        return seccionService.getSeccionById(id).map(seccion -> {
            seccionService.deleteSeccion(id);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
