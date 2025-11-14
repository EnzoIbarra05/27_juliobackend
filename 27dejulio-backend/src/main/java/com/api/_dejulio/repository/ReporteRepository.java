package com.api._dejulio.repository;

import com.api._dejulio.Entityes.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Integer> {
    
 //-----------------------QUERYS SEGURIDAD----------------------------   
    

    @Query(value = "SELECT COUNT(*) FROM reporte r " +
                   "WHERE r.indicador_id = 1 AND r.valor = 0 " +
                   "AND MONTH(r.fecha) = MONTH(CURRENT_DATE()) " +
                   "AND YEAR(r.fecha) = YEAR(CURRENT_DATE())"+
                   "AND r.estado = 'ALTA'",
           nativeQuery = true)
    int countAccidentesMes();
    
    @Query(value = "SELECT COUNT(*) FROM reporte r " +
               "WHERE r.indicador_id = 2 " +
               "AND MONTH(r.fecha) = MONTH(CURRENT_DATE()) " +
               "AND YEAR(r.fecha) = YEAR(CURRENT_DATE()) " +
               "AND r.estado = 'ALTA'",
       nativeQuery = true)
int countIncidentesMes();


@Query(value = "SELECT COUNT(*) FROM reporte r " +
               "WHERE r.indicador_id = 1 " +  // <--- agregué un espacio después del 1
               "AND MONTH(r.fecha) = MONTH(CURRENT_DATE()) " +
               "AND YEAR(r.fecha) = YEAR(CURRENT_DATE()) " + // <--- también agregué espacio final
               "AND r.estado = 'ALTA'",
       nativeQuery = true)
int countAccidentesMesGrafico();


@Query(value = "SELECT COUNT(*) FROM reporte r " +
               "WHERE r.indicador_id = 3 " +
               "AND MONTH(r.fecha) = MONTH(CURRENT_DATE()) " +
               "AND YEAR(r.fecha) = YEAR(CURRENT_DATE()) " +
               "AND r.estado = 'ALTA'",
       nativeQuery = true)
int countCapacitacionesMes();

//-------------------------------------------------------------------
    
//-------------------QUERYS CALIDAD----------------------------------
    
@Query(value = "SELECT COUNT(*) FROM reporte r " +
               "WHERE r.indicador_id = 4 AND r.valor = 0 " +
               "AND MONTH(r.fecha) = MONTH(CURRENT_DATE()) " +
               "AND YEAR(r.fecha) = YEAR(CURRENT_DATE()) " +
               "AND r.estado = 'ALTA'",
       nativeQuery = true)
int countOrdenyLimpieza();

@Query(value = "SELECT COUNT(*) FROM reporte r " +
               "WHERE r.indicador_id = 4 " +
               "AND MONTH(r.fecha) = MONTH(CURRENT_DATE()) " +
               "AND YEAR(r.fecha) = YEAR(CURRENT_DATE()) " +
               "AND r.estado = 'ALTA'",
       nativeQuery = true)
int countOrdenyLimpiezaGrafico();

@Query(value = "SELECT COUNT(*) FROM reporte r " +
               "WHERE r.indicador_id = 5 AND r.valor = 0 " +
               "AND MONTH(r.fecha) = MONTH(CURRENT_DATE()) " +
               "AND YEAR(r.fecha) = YEAR(CURRENT_DATE()) " +
               "AND r.estado = 'ALTA'",
       nativeQuery = true)
int countProcedimientosyTareas();

@Query(value = "SELECT COUNT(*) FROM reporte r " +
               "WHERE r.indicador_id = 5 " +
               "AND MONTH(r.fecha) = MONTH(CURRENT_DATE()) " +
               "AND YEAR(r.fecha) = YEAR(CURRENT_DATE()) " +
               "AND r.estado = 'ALTA'",
       nativeQuery = true)
int countProcedimientosyTareasGrafico();

@Query(value = "SELECT COUNT(*) FROM reporte r " +
               "WHERE r.indicador_id = 6 AND r.valor = 1 " +
               "AND MONTH(r.fecha) = MONTH(CURRENT_DATE()) " +
               "AND YEAR(r.fecha) = YEAR(CURRENT_DATE()) " +
               "AND r.estado = 'ALTA'",
       nativeQuery = true)
int countMaquinasyHerramientas();

@Query(value = "SELECT COUNT(*) FROM reporte r " +
               "WHERE r.indicador_id = 6 " +
               "AND MONTH(r.fecha) = MONTH(CURRENT_DATE()) " +
               "AND YEAR(r.fecha) = YEAR(CURRENT_DATE()) " +
               "AND r.estado = 'ALTA'",
       nativeQuery = true)
int countMaquinasyHerramientasGrafico();


//------------------- QUERYS MEDIO AMBIENTE --------------------------

@Query(value = "SELECT COUNT(*) FROM reporte r " +
               "WHERE r.indicador_id = 7 AND r.valor = 0 " +
               "AND MONTH(r.fecha) = MONTH(CURRENT_DATE()) " +
               "AND YEAR(r.fecha) = YEAR(CURRENT_DATE()) " +
               "AND r.estado = 'ALTA'",
       nativeQuery = true)
int countDisposicionDeResiduos();

@Query(value = "SELECT COUNT(*) FROM reporte r " +
               "WHERE r.indicador_id = 7 " +
               "AND MONTH(r.fecha) = MONTH(CURRENT_DATE()) " +
               "AND YEAR(r.fecha) = YEAR(CURRENT_DATE()) " +
               "AND r.estado = 'ALTA'",
       nativeQuery = true)
int countDisposicionDeResiduosGrafico();

@Query(value = "SELECT COUNT(*) FROM reporte r " +
               "WHERE r.indicador_id = 8 AND r.valor = 0 " +
               "AND MONTH(r.fecha) = MONTH(CURRENT_DATE()) " +
               "AND YEAR(r.fecha) = YEAR(CURRENT_DATE()) " +
               "AND r.estado = 'ALTA'",
       nativeQuery = true)
int countOtrosFactores();

@Query(value = "SELECT COUNT(*) FROM reporte r " +
               "WHERE r.indicador_id = 8 " +
               "AND MONTH(r.fecha) = MONTH(CURRENT_DATE()) " +
               "AND YEAR(r.fecha) = YEAR(CURRENT_DATE()) " +
               "AND r.estado = 'ALTA'",
       nativeQuery = true)
int countOtrosFactoresGrafico();


//------------------- QUERYS PRODUCTIVIDAD --------------------------

@Query(value = "SELECT COUNT(*) FROM reporte r " +
               "WHERE r.indicador_id = 9 AND r.valor = 0 " +
               "AND MONTH(r.fecha) = MONTH(CURRENT_DATE()) " +
               "AND YEAR(r.fecha) = YEAR(CURRENT_DATE()) " +
               "AND r.estado = 'ALTA'",
       nativeQuery = true)
int countProcedimientosyPlazos();

@Query(value = "SELECT COUNT(*) FROM reporte r " +
               "WHERE r.indicador_id = 9 " +
               "AND MONTH(r.fecha) = MONTH(CURRENT_DATE()) " +
               "AND YEAR(r.fecha) = YEAR(CURRENT_DATE()) " +
               "AND r.estado = 'ALTA'",
       nativeQuery = true)
int countProcedimientosyPlazosGrafico();


//-----------------------------------------------------------------
@Query("SELECT r FROM Reporte r " +
       "WHERE FUNCTION('MONTH', r.fecha) = :mes " +
       "AND FUNCTION('YEAR', r.fecha) = :anio " +
       "AND r.indicador.seccion.id = :seccionId " +
       "AND r.estado = 'ALTA'")
List<Reporte> findByMesAndAnioAndSeccion(@Param("mes") int mes,
                                         @Param("anio") int anio,
                                         @Param("seccionId") int seccionId);


}