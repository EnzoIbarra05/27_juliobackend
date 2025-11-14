
package com.api._dejulio.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class CoeficienteDTO {

private int CoeficienteSeguridad ; 
private int CoeficienteCalidad ; 
private int CoeficienteMedioAmbiente ; 
private int CoeficienteProductividad ; 
private double PorcentajeTotal;

}
