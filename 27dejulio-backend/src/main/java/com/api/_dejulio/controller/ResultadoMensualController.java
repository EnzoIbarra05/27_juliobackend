package com.api._dejulio.controller;

import com.api._dejulio.Entityes.Seccion;
import com.api._dejulio.Entityes.resultadoMensual;
import com.api._dejulio.dto.ReporteMensualDTO;
import com.api._dejulio.repository.SeccionRepository;
import com.api._dejulio.service.ReporteService;
import com.api._dejulio.service.ResultadoMensualService;
import com.api._dejulio.service.SeccionService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resultados")
public class ResultadoMensualController {

    @Autowired
    private ResultadoMensualService resultadoMensualService;
    
     @Autowired
    private SeccionRepository seccionRepository;
     
   @Autowired
    private ReporteService reporteService;
    
    // Obtener todos los resultados
    @GetMapping
    public List<resultadoMensual> getAllResultados() {
        return resultadoMensualService.getAllResultados();
    }

    // Obtener resultado por id
    @GetMapping("/{id}")
    public ResponseEntity<resultadoMensual> getResultadoById(@PathVariable int id) {
        return resultadoMensualService.getResultadoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

@GetMapping("/{id}/archivo")
public ResponseEntity<byte[]> descargarArchivo(@PathVariable int id) {
    resultadoMensual resultado = resultadoMensualService
            .buscarPorId(id)
            .orElseThrow(() -> new RuntimeException("Resultado no encontrado"));

    // Obtenemos la ruta del archivo
    String rutaArchivo = resultado.getRutaArchivo();
    if (rutaArchivo == null || rutaArchivo.isEmpty()) {
        return ResponseEntity.notFound().build();
    }

    try {
        Path path = Paths.get(rutaArchivo);
        byte[] archivo = Files.readAllBytes(path);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + path.getFileName())
                .contentType(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM)
                .body(archivo);

    } catch (IOException e) {
        return ResponseEntity.status(500).body(null);
    }
}    
    // Crear un nuevo resultado
@PostMapping
public ResponseEntity<?> createResultado(@RequestBody ReporteMensualDTO request) {
    try {
        // 1️ Buscar la sección
        Seccion seccion = seccionRepository.findById(request.getSeccionId())
                .orElseThrow(() -> new RuntimeException("Sección no encontrada"));
        
        double porcentaje = 0;
         
        String seccionEncontrada= seccion.getNombre();
        
        switch(seccionEncontrada){
        
            case "Seguridad":
                porcentaje = reporteService.calcularPorcentajeSeguridad();
                break;
            
            case "Calidad":
                porcentaje = reporteService.calcularPorcentajeCalidad();
                break;
                
            case "Medio Ambiente":
                porcentaje = reporteService.calcularPorcentajeMA();
                break;
                
            case "Productividad":
                porcentaje = reporteService.calcularPorcentajeProductividad();
                break; 
           
             default:
                 porcentaje = 100.00;
                 break;
 
        }       
        
        // 2️ Crear entidad resultadoMensual
        resultadoMensual resultado = new resultadoMensual();
        resultado.setMes(request.getMes());
        resultado.setPorcentaje(porcentaje);
        resultado.setAnio(request.getAnio());
        resultado.setFecha(request.getFecha());
        resultado.setSeccion(seccion); 

        // 3️ Pasar al service
        resultadoMensual guardado = resultadoMensualService.generarReporteMensual(resultado);

        // 4️ Devolver respuesta
        return ResponseEntity.ok(guardado);

    } catch (Exception e) {
        return ResponseEntity.badRequest().body("❌ Error al generar reporte mensual: " + e.getMessage());
    }
}




    // Actualizar un resultado existente
    @PutMapping("/{id}")
    public ResponseEntity<resultadoMensual> updateResultado(@PathVariable int id, @RequestBody resultadoMensual resultadoDetails) {
        return resultadoMensualService.getResultadoById(id).map(resultado -> {
            resultado.setMes(resultadoDetails.getMes());
            resultado.setAnio(resultadoDetails.getAnio());
            resultado.setPorcentaje(resultadoDetails.getPorcentaje());
            resultado.setFecha(resultadoDetails.getFecha());
            resultado.setSeccion(resultadoDetails.getSeccion());
            resultadoMensual updated = resultadoMensualService.saveResultado(resultado);
            return ResponseEntity.ok(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

   @DeleteMapping("/{id}")
public ResponseEntity<Void> deleteReporteMensual(@PathVariable int id) {
    return resultadoMensualService.getResultadoById(id).map(resultado -> {
        // 🚩 En vez de borrar, marcamos estado como "baja"
        resultado.setEstado("BAJA");
        resultadoMensualService.updateReporte(resultado); // guardamos el cambio
        return ResponseEntity.noContent().<Void>build();
    }).orElse(ResponseEntity.notFound().build());
}

@PutMapping("/{id}/alta")
public ResponseEntity<Void> darDeAlta(@PathVariable int id) {
    return resultadoMensualService.getResultadoById(id).map(resultado -> {
        resultado.setEstado("ALTA");
        resultadoMensualService.updateReporte(resultado);
        return ResponseEntity.noContent().<Void>build();
    }).orElse(ResponseEntity.notFound().build());
}

    //------------------------------------------------------------------------------------------------------------------

}
