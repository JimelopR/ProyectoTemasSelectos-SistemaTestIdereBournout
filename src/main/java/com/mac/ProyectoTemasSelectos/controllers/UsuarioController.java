/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mac.ProyectoTemasSelectos.controllers;

import com.mac.ProyectoTemasSelectos.dtos.ResultadoTestDTO;
import com.mac.ProyectoTemasSelectos.models.UsuarioModel;
import com.mac.ProyectoTemasSelectos.services.TestMostrarService;
import com.mac.ProyectoTemasSelectos.utils.CustomUserDetailsUtil;
import com.mac.ProyectoTemasSelectos.utils.JwtUtil;
import com.mac.ProyectoTemasSelectos.utils.PdfGeneradorUtil;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
      
    @Autowired
    private TestMostrarService testMostrarService;
    @Autowired
    private PdfGeneradorUtil pdfGeneratorService;

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
                    respuesta.put("id", usuarioConNombre.getId());
                    
                } else {
                    respuesta.put("nombre", "Nombre no disponible");
                }
                 return ResponseEntity.ok(respuesta); 
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Credenciales inválidas"));
        }
  
    } 

    @GetMapping("/obtenerResultado/{idTestAsignado}")
    public ResponseEntity<ResultadoTestDTO> obtenerResultado(@PathVariable Long idTestAsignado) {
        ResultadoTestDTO resultado = testMostrarService.obtenerResultado(idTestAsignado);
        return ResponseEntity.ok(resultado);
    }
    @GetMapping("/descargar-pdf/{idAsignacion}")
    public ResponseEntity<byte[]> downloadTestResultPdf(@PathVariable Long idAsignacion) {
        try {
            byte[] pdfBytes = pdfGeneratorService.generarTestResultadoPdfFromHtml(idAsignacion); // Llama al nuevo método

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "resultado_test_" + idAsignacion + ".pdf");
            headers.setContentLength(pdfBytes.length);

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);

        } catch (IOException e) {
            // Manejo de errores de IO o generación de PDF
            return new ResponseEntity<>(("Error al generar el PDF: " + e.getMessage()).getBytes(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (RuntimeException e) {
            // Manejo de test no encontrado u otros errores de negocio
            return new ResponseEntity<>(e.getMessage().getBytes(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Manejo de cualquier otra excepción inesperada
            return new ResponseEntity<>(("Error inesperado: " + e.getMessage()).getBytes(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
