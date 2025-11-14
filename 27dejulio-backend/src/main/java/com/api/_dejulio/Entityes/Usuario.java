
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
@Table(name = "usuarios") 
public class Usuario {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int id;
    
    
    private String nombre;
    private String apellido;
    private String usuario;
    private String contrasenia;
    
   @JsonIgnore 
   @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<Reporte> reportes;
   
   
}
