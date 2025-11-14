
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
public class productividadDTO {

@JsonProperty("Procedimientos y Plazos")
private int procedimientos_y_Plazos;
}
