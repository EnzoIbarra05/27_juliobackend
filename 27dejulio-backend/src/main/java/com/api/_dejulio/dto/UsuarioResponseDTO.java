
package com.api._dejulio.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioResponseDTO {
    private int id;
    private String nombre;
    private String apellido;
    private String usuario;
}
