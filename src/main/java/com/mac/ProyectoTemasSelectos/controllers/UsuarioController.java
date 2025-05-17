/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mac.ProyectoTemasSelectos.controllers;

import com.mac.ProyectoTemasSelectos.models.UsuarioModel;
import com.mac.ProyectoTemasSelectos.utils.CustomUserDetailsUtil;
import com.mac.ProyectoTemasSelectos.utils.JwtUtil;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jimena
 */

@RestController
public class UsuarioController {
    @Autowired
    private JwtUtil jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/loginUsuario")
    public ResponseEntity<Map<String, Object>> login(@RequestBody UsuarioModel usuarioModel) {
         System.out.println("Recibiendo petición de login");
          System.out.println("USUARIO ingresada: " + usuarioModel.getCorreo());
            System.out.println("Contraseña ingresada: " + usuarioModel.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuarioModel.getCorreo(), usuarioModel.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            final String jwt = jwtService.generateToken(userDetails);
               
                    Map<String, Object> respuesta = new HashMap<>();
                        respuesta.put("message", "Login successful");
                        respuesta.put("token", jwt);
                    
                    // Obtener la colección de GrantedAuthority
                    Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

                    String role = null;
                    if (!authorities.isEmpty()) {
                        role = authorities.iterator().next().getAuthority();
                    }
                    respuesta.put("role",role);
                    // Agregamos el nombre del usuario a la respuesta
   
      
                if (userDetails instanceof CustomUserDetailsUtil.UsuarioConNombre usuarioConNombre) {
                    respuesta.put("nombre", usuarioConNombre.getNombre());
                } else {
                    respuesta.put("nombre", "Nombre no disponible");
                }
                 return ResponseEntity.ok(respuesta); 
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Credenciales inválidas"));
        }
  
    }   
}
