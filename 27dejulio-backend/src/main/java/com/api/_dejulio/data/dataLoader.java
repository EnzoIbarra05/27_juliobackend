
package com.api._dejulio.data;

import com.api._dejulio.Entityes.Indicador;
import com.api._dejulio.Entityes.Seccion;
import com.api._dejulio.Entityes.Usuario;
import com.api._dejulio.dto.IndicadorDTO;
import com.api._dejulio.repository.IndicadorRepository;
import com.api._dejulio.repository.SeccionRepository;
import com.api._dejulio.repository.UsuarioRepository;
import com.api._dejulio.service.SeccionService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 *
 * @author enzoi
 */
@Component
public class dataLoader implements CommandLineRunner {

@Autowired
private UsuarioRepository usuarioRepository;

@Autowired 
private SeccionRepository seccionRepository;

@Autowired
private IndicadorRepository indicadorRepository;

@Autowired
private SeccionService seccionService;

 @Override
    public void run(String... args) throws Exception {

        // Si no existe el usuario admin, lo creo
        if (usuarioRepository.findByApellido("Ibarra").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setNombre("Enzo");
            admin.setApellido("Ibarra");
            admin.setContrasenia("27dejuliokpi"); 
            admin.setUsuario("Enzoibarra");

            usuarioRepository.save(admin);
            System.out.println("✅ Usuario creado");
        }

        // Podés crear más usuarios predeterminados
        if (usuarioRepository.findByApellido("Prospero").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setNombre("Javier");
            admin.setApellido("Prospero");
            admin.setContrasenia("27dejuliokpi"); 
            admin.setUsuario("Javiprospero");

            usuarioRepository.save(admin);
            System.out.println("✅ Usuario creado");
        }
        if (usuarioRepository.findByApellido("Corvalan").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setNombre("Julio");
            admin.setApellido("Corvalan");
            admin.setContrasenia("27dejuliokpi"); 
            admin.setUsuario("Juliocorvalan");

            usuarioRepository.save(admin);
            System.out.println("✅ Usuario creado");
        }
        if (usuarioRepository.findByApellido("Aguilera").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setNombre("Fernando");
            admin.setApellido("Aguilera");
            admin.setContrasenia("27dejuliokpi"); 
            admin.setUsuario("Feraguilera");

            usuarioRepository.save(admin);
            System.out.println("✅ Usuario creado");
        }
        if (usuarioRepository.findByApellido("27dejulio").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setNombre("Empresa");
            admin.setApellido("27dejulio");
            admin.setContrasenia("27dejuliokpi"); 
            admin.setUsuario("27dejuliosrl");

            usuarioRepository.save(admin);
            System.out.println("✅ Usuario creado");
        }
        if (usuarioRepository.findByApellido("Refineria").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setNombre("Ypf");
            admin.setApellido("Refineria");
            admin.setContrasenia("27dejuliokpi"); 
            admin.setUsuario("Ypfrefineria");

            usuarioRepository.save(admin);
            System.out.println("✅ Usuario creado");
        }
        if (seccionRepository.findByNombre("Seguridad").isEmpty()) {
    Seccion seccion = new Seccion();
    seccion.setNombre("Seguridad");
    seccionRepository.save(seccion); // ✅ GUARDA en la base de datos
}

if (seccionRepository.findByNombre("Calidad").isEmpty()) {
    Seccion seccion = new Seccion();
    seccion.setNombre("Calidad");
    seccionRepository.save(seccion); // ✅
}

if (seccionRepository.findByNombre("Medio Ambiente").isEmpty()) {
    Seccion seccion = new Seccion();
    seccion.setNombre("Medio Ambiente");
    seccionRepository.save(seccion); // ✅
}

if (seccionRepository.findByNombre("Productividad").isEmpty()) {
    Seccion seccion = new Seccion();
    seccion.setNombre("Productividad");
    seccionRepository.save(seccion); // ✅
}
if (indicadorRepository.findByNombre("Accidente").isEmpty()) {
    
 Optional<Seccion> seguridadOpt = seccionRepository.findByNombre("Seguridad");

if (seguridadOpt.isPresent() && indicadorRepository.findByNombre("Accidente").isEmpty()) {
    Indicador indicador = new Indicador();
    indicador.setCodigo("S0");
    indicador.setNombre("Accidente");
    indicador.setSeccion(seguridadOpt.get());
    indicadorRepository.save(indicador);
}
}

if (indicadorRepository.findByNombre("Incidente").isEmpty()) {
    
 Optional<Seccion> seguridadOpt = seccionRepository.findByNombre("Seguridad");

if (seguridadOpt.isPresent() && indicadorRepository.findByNombre("Incidente").isEmpty()) {
    Indicador indicador = new Indicador();
    indicador.setCodigo("S1");
    indicador.setNombre("Incidente");
    indicador.setSeccion(seguridadOpt.get());
    indicadorRepository.save(indicador);
}
}
if (indicadorRepository.findByNombre("Capacitacion").isEmpty()) {
    
 Optional<Seccion> seguridadOpt = seccionRepository.findByNombre("Seguridad");

if (seguridadOpt.isPresent() && indicadorRepository.findByNombre("Capacitacion").isEmpty()) {
    Indicador indicador = new Indicador();
    indicador.setCodigo("S2");
    indicador.setNombre("Capacitacion");
    indicador.setSeccion(seguridadOpt.get());
    indicadorRepository.save(indicador);
}
}
if (indicadorRepository.findByNombre("Orden y Limpieza").isEmpty()) {
    
 Optional<Seccion> seguridadOpt = seccionRepository.findByNombre("Calidad");

if (seguridadOpt.isPresent() && indicadorRepository.findByNombre("Orden y Limpieza").isEmpty()) {
    Indicador indicador = new Indicador();
    indicador.setCodigo("C0");
    indicador.setNombre("Orden y Limpieza");
    indicador.setSeccion(seguridadOpt.get());
    indicadorRepository.save(indicador);
}
}
if (indicadorRepository.findByNombre("Procedimientos y Tareas").isEmpty()) {
    
 Optional<Seccion> seguridadOpt = seccionRepository.findByNombre("Calidad");

if (seguridadOpt.isPresent() && indicadorRepository.findByNombre("Procedimientos y Tareas").isEmpty()) {
    Indicador indicador = new Indicador();
    indicador.setCodigo("C1");
    indicador.setNombre("Procedimientos y Tareas");
    indicador.setSeccion(seguridadOpt.get());
    indicadorRepository.save(indicador);
}
}
if (indicadorRepository.findByNombre("Maquinas y Herramientas").isEmpty()) {
    
 Optional<Seccion> seguridadOpt = seccionRepository.findByNombre("Calidad");

if (seguridadOpt.isPresent() && indicadorRepository.findByNombre("Maquinas y Herramientas").isEmpty()) {
    Indicador indicador = new Indicador();
    indicador.setCodigo("C2");
    indicador.setNombre("Maquinas y Herramientas");
    indicador.setSeccion(seguridadOpt.get());
    indicadorRepository.save(indicador);
}
}
if (indicadorRepository.findByNombre("Residuos").isEmpty()) {
    
 Optional<Seccion> seguridadOpt = seccionRepository.findByNombre("Medio Ambiente");

if (seguridadOpt.isPresent() && indicadorRepository.findByNombre("Residuos").isEmpty()) {
    Indicador indicador = new Indicador();
    indicador.setCodigo("MA0");
    indicador.setNombre("Residuos");
    indicador.setSeccion(seguridadOpt.get());
    indicadorRepository.save(indicador);
}
}
if (indicadorRepository.findByNombre("Otros factores").isEmpty()) {
    
 Optional<Seccion> seguridadOpt = seccionRepository.findByNombre("Medio Ambiente");

if (seguridadOpt.isPresent() && indicadorRepository.findByNombre("Otros factores").isEmpty()) {
    Indicador indicador = new Indicador();
    indicador.setCodigo("MA1");
    indicador.setNombre("Otros factores");
    indicador.setSeccion(seguridadOpt.get());
    indicadorRepository.save(indicador);
}
}
if (indicadorRepository.findByNombre("Procedimientos y Plazos").isEmpty()) {
    
 Optional<Seccion> seguridadOpt = seccionRepository.findByNombre("Productividad");

if (seguridadOpt.isPresent() && indicadorRepository.findByNombre("Procedimientos y Plazos").isEmpty()) {
    Indicador indicador = new Indicador();
    indicador.setCodigo("P0");
    indicador.setNombre("Procedimientos y Plazos");
    indicador.setSeccion(seguridadOpt.get());
    indicadorRepository.save(indicador);
}
}

    }    
}
