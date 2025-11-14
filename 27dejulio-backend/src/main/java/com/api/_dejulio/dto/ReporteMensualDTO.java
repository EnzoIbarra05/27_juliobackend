
package com.api._dejulio.dto;

import java.util.Date;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReporteMensualDTO {
    private int id;
    private int mes;
    private int anio;
    private Date fecha;
    private int seccionId; 

 
}
