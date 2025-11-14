
package com.api._dejulio.Entityes;

import jakarta.persistence.*;
import java.util.Date;
import lombok.*;

/**
 *
 * @author VISUA
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reporte")
@Getter
@Setter
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date fecha;
    private String descripcion;
    private int valor;
    private String estado;

    // ✅ En vez de LONGBLOB guardamos la ruta
    private String rutaArchivo;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "indicador_id", nullable = false)
    private Indicador indicador;
}
