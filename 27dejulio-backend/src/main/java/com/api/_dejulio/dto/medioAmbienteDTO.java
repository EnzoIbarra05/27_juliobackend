
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
public class medioAmbienteDTO {

private int Residuos;
@JsonProperty("Otros Factores")
private int otros_Factores;

}
