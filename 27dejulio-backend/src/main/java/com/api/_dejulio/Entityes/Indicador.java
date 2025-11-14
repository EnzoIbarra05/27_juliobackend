
package com.api._dejulio.Entityes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;
import lombok.*;

/**
 *
 * @author VISUA
 */
@Entity
@Data
@NoArgsConstructor 
@AllArgsConstructor
@Table(name = "indicador")
public class Indicador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nombre;
    private String codigo;
    
    @ManyToOne
    @JoinColumn(name = "seccion_id", nullable = false)
    private Seccion seccion;
    
    @JsonIgnore
    @OneToMany(mappedBy = "indicador", cascade = CascadeType.ALL, orphanRemoval = true)
     private List<Reporte> reportes;
    
}
