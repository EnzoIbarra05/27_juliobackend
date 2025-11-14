package com.api._dejulio.controller;

import com.api._dejulio.Entityes.*;
import com.api._dejulio.dto.*;
import com.api._dejulio.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;
import org.springframework.core.io.*;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private IndicadorService indicadorService;

    // Obtener todos los reportes
    
    @GetMapping("/reporteInfoTabla")
    public List<ReporteDTOget> getAllReportes() {
    return reporteService.getAllReportesDTO();
}
    // Obtener reporte por id
    @GetMapping("/{id}")
    public ResponseEntity<Reporte> getReporteById(@PathVariable int id) {
        return reporteService.getReporteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un reporte con archivo y DTO
@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<Reporte> createReporte(
        @RequestParam("reporte") String reporteJson,
        @RequestPart(value = "file", required = false) MultipartFile file) {
    try {
        ObjectMapper mapper = new ObjectMapper();
        ReporteDTO dto = mapper.readValue(reporteJson, ReporteDTO.class);

        Reporte reporte = new Reporte();
        reporte.setFecha(dto.getFecha());
        reporte.setDescripcion(dto.getDescripcion());
        reporte.setValor(dto.getValor());
        reporte.setUsuario(usuarioService.getUsuarioById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado")));
        reporte.setIndicador(indicadorService.getIndicadorById(dto.getIndicadorId())
                .orElseThrow(() -> new RuntimeException("Indicador no encontrado")));

        Reporte saved = reporteService.saveReporte(reporte, file);
        return ResponseEntity.ok(saved);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.badRequest().build();
    }
}



    // Descargar archivo de un reporte
@GetMapping("/{id}/archivo")
public ResponseEntity<Resource> downloadArchivo(@PathVariable int id) {
    Optional<Reporte> optionalReporte = reporteService.getReporteById(id);

    if (optionalReporte.isEmpty()) {
        return ResponseEntity.notFound().build();
    }

    Reporte reporte = optionalReporte.get();
    if (reporte.getRutaArchivo() == null || reporte.getRutaArchivo().isEmpty()) {
        return ResponseEntity.notFound().build();
    }

    try {
        Path path = Paths.get(reporte.getRutaArchivo());
        Resource recurso = new UrlResource(path.toUri());

        if (!recurso.exists()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF) // o APPLICATION_OCTET_STREAM si querés genérico
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=" + path.getFileName().toString())
                .body(recurso);

    } catch (Exception e) {
        return ResponseEntity.internalServerError().build();
    }
}


    // Eliminar un reporte logicamente
  @DeleteMapping("/{id}")
public ResponseEntity<Void> deleteReporte(@PathVariable int id) {
    return reporteService.getReporteById(id).map(reporte -> {
        // 🚩 En vez de borrar, marcamos estado como "baja"
        reporte.setEstado("BAJA");
        reporteService.updateReporte(reporte); // guardamos el cambio
        return ResponseEntity.noContent().<Void>build();
    }).orElse(ResponseEntity.notFound().build());
}

@PutMapping("/{id}/alta")
public ResponseEntity<Void> darDeAlta(@PathVariable int id) {
    return reporteService.getReporteById(id).map(reporte -> {
        reporte.setEstado("ALTA");
        reporteService.updateReporte(reporte);
        return ResponseEntity.noContent().<Void>build();
    }).orElse(ResponseEntity.notFound().build());
}


    
    @GetMapping("/porcentaje-seguridad")
    public ResponseEntity<Double> getPorcentajeSeguridad() {
    double porcentaje = reporteService.calcularPorcentajeSeguridad();
    return ResponseEntity.ok(porcentaje);
}
    
    @GetMapping("/porcentaje-calidad")
    public ResponseEntity<Double> getPorcentajeCalidad(){
    double porcentaje = reporteService.calcularPorcentajeCalidad();
    return ResponseEntity.ok(porcentaje);
    }
    
    @GetMapping ("/porcentaje-medioAmbiente")
    public ResponseEntity<Double> getPorcentajeMedioAmbiente(){
    double porcentaje = reporteService.calcularPorcentajeMA();
    return ResponseEntity.ok(porcentaje);
    }
    
    @GetMapping ("/porcentaje-productividad")
    public ResponseEntity<Double> getPorcentajeProductividad(){
    double porcentaje = reporteService.calcularPorcentajeProductividad();
    return ResponseEntity.ok(porcentaje);
    }
    
    @GetMapping("/grafico-seguridad")
    public ResponseEntity<seguridadDTO> getSituacionSeguridad() {
    seguridadDTO graficoServicio = reporteService.graficoSeguridad();
    return ResponseEntity.ok(graficoServicio);
    }
    @GetMapping("/grafico-calidad")
    public ResponseEntity<calidadDTO> getSituacionCalidad() {
    calidadDTO graficoCalidad = reporteService.graficoCalidad();
    return ResponseEntity.ok(graficoCalidad);
    }
    @GetMapping("/grafico-medioambiente")
    public ResponseEntity<medioAmbienteDTO> getSituacionMedioAmbiente() {
    medioAmbienteDTO graficoMedioAmbiente = reporteService.graficoMedioAmbiente();
    return ResponseEntity.ok(graficoMedioAmbiente);
    }
    @GetMapping("/grafico-productividad")
    public ResponseEntity<productividadDTO> getSituacionProductividad() {
    productividadDTO graficoProductividad = reporteService.graficoProductividad();
    return ResponseEntity.ok(graficoProductividad);
    }
    
    @GetMapping("/coeficientes")
    public ResponseEntity<CoeficienteDTO> getCoeficientes(){
    CoeficienteDTO coeficiente = reporteService.coeficientes();
    return ResponseEntity.ok(coeficiente);
    }

}
