
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
@Table(name = "seccion")
public class Seccion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; 
    
    private String nombre;
    
    @JsonIgnore
    @OneToMany(mappedBy = "seccion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<resultadoMensual> resultadoMensual;
    
    @JsonIgnore
    @OneToMany(mappedBy = "seccion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Indicador> indicador; 
    
}
