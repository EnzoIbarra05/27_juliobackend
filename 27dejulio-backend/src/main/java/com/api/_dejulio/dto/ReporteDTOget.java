
package com.api._dejulio.dto;

import lombok.Data;
import java.util.Date;

@Data
public class ReporteDTOget {
    private int id;
    private Date fecha;
    private String descripcion;
    private int valor;

    private String usuarioNombre;     
    private String indicadorNombre;
    private String seccionNombre;  
    private String codigoIndicador; 
    private String estado;
}
