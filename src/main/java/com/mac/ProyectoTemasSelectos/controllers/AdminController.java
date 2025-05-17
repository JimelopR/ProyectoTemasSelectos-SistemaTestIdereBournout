/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mac.ProyectoTemasSelectos.controllers;

import com.mac.ProyectoTemasSelectos.dtos.EmailDTO;
import com.mac.ProyectoTemasSelectos.dtos.TestFormatoDTO;
import com.mac.ProyectoTemasSelectos.dtos.UsuarioDTO;
import com.mac.ProyectoTemasSelectos.models.UsuarioModel;
import com.mac.ProyectoTemasSelectos.services.TestService;
import com.mac.ProyectoTemasSelectos.services.UsuarioService;
import com.mac.ProyectoTemasSelectos.utils.EmailUtil;
import jakarta.mail.MessagingException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jimena
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private TestService testService;
    @Autowired
    private EmailUtil emailService;


    // ------------------ GESTIÓN DE USUARIOS ------------------
    @PostMapping("/registrarUsuario")
    private ResponseEntity<String> registrarUsuario(@RequestBody UsuarioModel usuario){   
        try {
             // Generamos una contraseña aleatoria
            String contrasenaGenerada = usuarioService.generarContrasenaAleatoria();
            usuario.setPassword(contrasenaGenerada); // Asignamos la contraseña generada al usuario
            
            // Guardamos el usuario con la nueva contraseña
            UsuarioModel nuevoUsuario = usuarioService.guardarUsuario(usuario);
            
            // Creamos el modelo de correo
            EmailDTO email = new EmailDTO();
            
            email.setUsuario(nuevoUsuario.getCorreo()); // El correo del usuario
            email.setAsunto("Bienvenido a nuestra plataforma"); // Asunto del correo
            email.setMensaje("Hola " + nuevoUsuario.getNombre() + ",\n\nTu cuenta ha sido registrada exitosamente.\nTu contraseña es: " + contrasenaGenerada);
            emailService.sendMail(email);
        
        } catch (MessagingException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, "Error al enviar correo: ", ex);
            return new ResponseEntity<>("Error al enviar correo", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, "Error inesperado: ", ex);
            return new ResponseEntity<>("Error inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Correo enviado exitosamente", HttpStatus.OK);
    }
    
    @GetMapping("/consultarUsuarios")
    private ResponseEntity<List<UsuarioDTO>> getUsuarios() {

        List<UsuarioDTO> usuarios = usuarioService.obtenerTodosLosUsuarios();

        if (usuarios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
        }
    }

/*  

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuario) {
        usuarioService.actualizarUsuario(id, usuario);
        return ResponseEntity.ok("Usuario actualizado correctamente.");
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok("Usuario eliminado correctamente.");
    }*/

    // ------------------ GESTIÓN DE TESTS ------------------

    @GetMapping("/tests")
    public ResponseEntity<List<TestFormatoDTO>> obtenerTests() {
        return ResponseEntity.ok(testService.obtenerTodos());
    }

    /*@PostMapping("/tests")
    public ResponseEntity<?> crearTest(@RequestBody TestModel test) {
        testService.crearTest(test);
        return ResponseEntity.ok("Test creado correctamente.");
    }

    @PutMapping("/tests/{id}")
    public ResponseEntity<?> actualizarTest(@PathVariable Long id, @RequestBody TestModel test) {
        testService.actualizarTest(id, test);
        return ResponseEntity.ok("Test actualizado correctamente.");
    }*/

    @DeleteMapping("/tests/{id}")
    public ResponseEntity<?> eliminarTest(@PathVariable Long id) {
        testService.eliminarTest(id);
        return ResponseEntity.ok("Test eliminado correctamente.");
    }
}
