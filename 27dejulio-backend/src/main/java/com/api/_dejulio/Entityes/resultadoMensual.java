
package com.api._dejulio.Entityes;

import jakarta.persistence.*;
import java.util.Date;
import lombok.*;


@Entity
@Table(name = "Reporte_Mensual")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class resultadoMensual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int mes;
    private int anio;
    private Double porcentaje;
    private Date fecha;
    private String estado;

    // 📌 Ahora guardamos solo la ruta
    @Column(name = "ruta_archivo")
    private String rutaArchivo;

    @ManyToOne
    @JoinColumn(name = "Seccion_id", nullable = false)
    private Seccion seccion;
}

