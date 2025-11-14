
package com.api._dejulio.dto;

/**
 *
 * @author VISUA
 */
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class seguridadDTO {
    
    private int Incidentes;
    
    private int Accidentes;
    
    private int Capacitaciones;
}
