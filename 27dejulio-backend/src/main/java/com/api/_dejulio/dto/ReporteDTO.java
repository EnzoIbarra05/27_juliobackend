package com.api._dejulio.dto;

import lombok.Data;
import java.util.Date;

@Data
public class ReporteDTO {
    private Date fecha;
    private String descripcion;
    private int valor;
    private int usuarioId;     // ID del usuario
    private int indicadorId;   // ID del indicador
}
