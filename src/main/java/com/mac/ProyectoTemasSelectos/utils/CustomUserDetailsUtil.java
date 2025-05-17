/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mac.ProyectoTemasSelectos.utils;

/**
 *
 * @author jimena
 */

import com.mac.ProyectoTemasSelectos.models.UsuarioModel;
import com.mac.ProyectoTemasSelectos.repositories.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsUtil implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    // Clase interna para representar un Usuario con nombre
    public class UsuarioConNombre extends User {
        private final String nombre;
        private final String apellidoPaterno;  // Agregamos apellidoPaterno
        private final String apellidoMaterno;  // Agregamos apellidoMaterno

        public UsuarioConNombre(String username, String password, boolean enabled, boolean accountNonExpired,
                                boolean credentialsNonExpired, boolean accountNonLocked,
                                List<SimpleGrantedAuthority> authorities, String nombre, String apellidoPaterno, String apellidoMaterno) { // Modificamos el constructor
            super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
            this.nombre = nombre;
            this.apellidoPaterno = apellidoPaterno;
            this.apellidoMaterno = apellidoMaterno;
        }

        public String getNombre() {
            return nombre + " " + apellidoPaterno + " " + apellidoMaterno; // Modificamos el método para concatenar
        }
    }


    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {  
       
        Optional<UsuarioModel> usuarioOpt = Optional.empty();  // Iniciar como Optional vacío
        try {
        // Intentamos obtener el usuario por correo
            usuarioOpt = usuarioRepository.findByCorreo(correo);
            if (usuarioOpt.isPresent()) {
                System.out.println("Usuario encontrado: " + usuarioOpt.get().getCorreo());
            } else {
                System.out.println("Usuario con correo " + correo + " no encontrado.");
            }
        } catch (Exception e) {
            // Log de la excepción para entender el problema
            System.err.println("Error al buscar el usuario con correo " + correo + ": " + e.getMessage());
        }
        

        if (usuarioOpt.isEmpty()) {
            throw new UsernameNotFoundException("Usuario no encontrado con correo: " + correo);
        }

        UsuarioModel usuario = usuarioOpt.get();
        
        String rol;
        rol = switch (usuario.getIdTipoUsuario()) {
            case 1 -> "ROLE_ADMINISTRADOR";
            case 2 -> "ROLE_EVALUADOR";
            case 3 -> "ROLE_EVALUADO";
            default -> "ROLE_USUARIO";
        }; 

        // Devuelve un objeto UserDetails que incluye el nombre
        return new UsuarioConNombre(
                usuario.getCorreo(),
                usuario.getPassword(),
                true, // Habilitado
                true, // accountNonExpired
                true, // credentialsNonExpired
                true, // accountNonLocked
                List.of(new SimpleGrantedAuthority(rol)), // Lista de autoridades
                usuario.getNombre(),  
                usuario.getApellidoPaterno(), 
                usuario.getApellidoMaterno() 
        );
    }
}
