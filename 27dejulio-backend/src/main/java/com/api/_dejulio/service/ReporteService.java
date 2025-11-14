package com.api._dejulio.service;

import com.api._dejulio.Entityes.Reporte;
import com.api._dejulio.dto.*;
import com.api._dejulio.repository.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;
    
        public List<ReporteDTOget> getAllReportesDTO() {
        return reporteRepository.findAll()
                .stream()
                .map(reporte -> {
                    ReporteDTOget dto = new ReporteDTOget();
                    dto.setFecha(reporte.getFecha());
                    dto.setDescripcion(reporte.getDescripcion());
                    dto.setValor(reporte.getValor());
                    dto.setEstado(reporte.getEstado());
                    dto.setId(reporte.getId());

                    // Mapear nombres en lugar de IDs
                    if (reporte.getUsuario() != null) {
                        dto.setUsuarioNombre(reporte.getUsuario().getNombre());
                    }
                    if (reporte.getIndicador() != null) {
                        dto.setIndicadorNombre(reporte.getIndicador().getNombre());
                    }
                    if (reporte.getIndicador() != null){
                      dto.setSeccionNombre(reporte.getIndicador().getSeccion().getNombre());
                }
                    
                if (reporte.getIndicador() != null) {
                        dto.setCodigoIndicador(reporte.getIndicador().getCodigo());
                    }

                    return dto;
                })
                .collect(Collectors.toList());
    }

    // Obtener todos los reportes
    public List<Reporte> getAllReportes() {
        return reporteRepository.findAll();
    }

    // Obtener reporte por id
    public Optional<Reporte> getReporteById(int id) {
        return reporteRepository.findById(id);
    }

    //------------------------- Guardar reporte con archivo ------------------------------------------
    public Reporte saveReporte(Reporte reporte, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            
            
            // 📂 Carpeta donde se guardan los reportes
            String carpeta = "uploads/reportes/";

            // ⚡ Asegurarse de que la carpeta exista
            Files.createDirectories(Paths.get(carpeta));

            // 📝 Nombre del archivo
            String nombreArchivo = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path path = Paths.get(carpeta + nombreArchivo);

            // Guardar archivo en el servidor
            Files.write(path, file.getBytes());

            // Guardar la ruta en la BD
            reporte.setRutaArchivo(path.toString());
            // Guardar estado de alta
            reporte.setEstado("ALTA");
        }

        return reporteRepository.save(reporte);
    }

   public Reporte updateReporte(Reporte reporte) {
    return reporteRepository.save(reporte);
}

    
    
    
    //------------------------- Porcentaje, grafico y coeficiente de Seguridad ------------------------------------------
    
    public double calcularPorcentajeSeguridad() {
        
        int incidentes = reporteRepository.countIncidentesMes();
        int accidentes = reporteRepository.countAccidentesMes();
        int cursos = reporteRepository.countCapacitacionesMes();

        int cantidadIncidentes = 0;
        int cantidadAccidentes = 1;
        int cantidadCursos = 0;

        if (incidentes <= 3) {
            cantidadIncidentes = 1;
        }
        if (accidentes >= 1) {
            cantidadAccidentes = 0;
        }
        if (cursos >= 1) {
            cantidadCursos = 1;
        }

        double porcentajeSeguridad = cantidadIncidentes * 33.33 +
                                     cantidadAccidentes * 33.33 +
                                     cantidadCursos * 33.33;

        return porcentajeSeguridad;
    }
    
    public seguridadDTO graficoSeguridad(){
    
    int incidentes = reporteRepository.countIncidentesMes();
    int accidentes =reporteRepository.countAccidentesMesGrafico();
    int capacitaciones = reporteRepository.countCapacitacionesMes();
    
    return new seguridadDTO (incidentes,accidentes,capacitaciones);
    }
    

 //-------------------------------------------------------------------------------------------------------------------- 
    
    
  //------------------------- Porcentaje, grafico y coeficiente de Calidad --------------------------------------------
    public double calcularPorcentajeCalidad(){
    int ordenyLimpieza = reporteRepository.countOrdenyLimpieza();
    int procedimientosyTareas = reporteRepository.countProcedimientosyTareas();
    int maquinasyHerramientas = reporteRepository.countMaquinasyHerramientas();
    
    int cantidadOL = 1;
    int cantidadPT = 1;
    int cantidadMH = 0;
  
    
    
    if (ordenyLimpieza > 0){
    cantidadOL= 0;
    }
    
    if (procedimientosyTareas > 0){
    cantidadPT= 0;
    }
        
    if (maquinasyHerramientas > 0){
    cantidadMH= 1;
    }
    
    double porcentajeCalidad = cantidadOL * 33.33 +
                               cantidadPT * 33.33 +
                               cantidadMH * 33.33;
     
     
     return porcentajeCalidad;
    }
    
   public calidadDTO graficoCalidad(){
    
    int ordenyLimpieza = reporteRepository.countOrdenyLimpiezaGrafico();
    int procedimientosyTareas =reporteRepository.countProcedimientosyTareasGrafico();
    int maquinasyHerramientas = reporteRepository.countMaquinasyHerramientasGrafico();
    
    return new calidadDTO (ordenyLimpieza, procedimientosyTareas, maquinasyHerramientas);
    }
   
  //-------------------------------------------------------------------------------------------------------------------
    
    
  //------------------------- Porcentaje, grafico y coeficiente de Medio Ambiente -------------------------------------
   
    public double calcularPorcentajeMA(){
        
    int disposicionResiduos = reporteRepository.countDisposicionDeResiduos();
    int otrosFactores= reporteRepository.countOtrosFactores();
    
    int cantidadDR= 1;
    int cantidadOF= 1;
    
    if (disposicionResiduos > 0 ){
     cantidadDR = 0;
    }
    if (otrosFactores > 0 ){
     cantidadOF = 0;
    }
    
    
   double porcentajeMA = cantidadDR * 50 + cantidadOF * 50; 
           
    return porcentajeMA;
    }
    
   public medioAmbienteDTO graficoMedioAmbiente(){
    
    int residuos = reporteRepository.countDisposicionDeResiduosGrafico();
    int otrosFactores =reporteRepository.countOtrosFactoresGrafico();
    
    return new medioAmbienteDTO (residuos,otrosFactores);
    }
    
  //------------------------------------------------------------------------------------------------------------------------- 
    
    
  //------------------------- Porcentaje, grafico y coeficiente de productividad --------------------------------------------
   
    public double calcularPorcentajeProductividad(){
    
        int procedimientosyPlazos = reporteRepository.countProcedimientosyPlazos();
        
        
        int cantidadPP = 1;
        
         if (procedimientosyPlazos > 0 ){
             cantidadPP = 0;
    }
    
        int porcentajeProductividad = cantidadPP * 100 ;
        
        return porcentajeProductividad;
    
    }
    
    public productividadDTO graficoProductividad(){
    
    int procedimientosyPlazos = reporteRepository.countProcedimientosyPlazosGrafico();
   
    return new productividadDTO (procedimientosyPlazos);
    }
   
    public CoeficienteDTO coeficientes (){
        
        int coeficienteS=0;
        int coeficienteC=0;
        int coeficienteMA=0;
        int coeficienteP=0;
        
    
        double porcentajeS= calcularPorcentajeSeguridad();
        double porcentajeC= calcularPorcentajeCalidad();
        double porcentajeMA= calcularPorcentajeMA();
        double porcentajeP= calcularPorcentajeProductividad();
        
        if (porcentajeS == 99.99){
        coeficienteS=1;
        porcentajeS=25;
        }else{
            porcentajeS=0;
        }
        if (porcentajeC == 99.99){
        coeficienteC=1;
        porcentajeC=25;
        }else{
        porcentajeC=0;
        }
        if (porcentajeMA == 100){
        coeficienteMA=1;
        porcentajeMA=25;
        }else{
        porcentajeMA=0;
        }
        if (porcentajeP == 100){
        coeficienteP=1;
        porcentajeP=25;
        }else{
        porcentajeP=0;
        }
        
        double porcentajeTotal= (porcentajeS+ porcentajeC+ porcentajeMA + porcentajeP) ;
 
     
        CoeficienteDTO coeficientes = new CoeficienteDTO();
        
        coeficientes.setCoeficienteSeguridad(coeficienteS);
        coeficientes.setCoeficienteCalidad(coeficienteC);
        coeficientes.setCoeficienteMedioAmbiente(coeficienteMA);
        coeficientes.setCoeficienteProductividad(coeficienteP);
        coeficientes.setPorcentajeTotal(porcentajeTotal);
        
        return coeficientes;
           
    }
    
    
}

