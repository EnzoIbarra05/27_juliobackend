
package com.api._dejulio.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author VISUA
 */

@Data
@AllArgsConstructor
public class calidadDTO {
    @JsonProperty("Orden y Limpieza")
    private int orden_y_Limpieza;

    @JsonProperty("Procedimientos y Tareas")
    private int procedimientos_y_Tareas;

    @JsonProperty("Maquinas y Herramientas")
    private int maquinas_y_Herramientas;
}
