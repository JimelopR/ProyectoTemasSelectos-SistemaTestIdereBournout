/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mac.ProyectoTemasSelectos.servicesImpl;

import com.mac.ProyectoTemasSelectos.dtos.UsuarioDTO;
import com.mac.ProyectoTemasSelectos.models.UsuarioModel;
import com.mac.ProyectoTemasSelectos.repositories.UsuarioRepository;
import com.mac.ProyectoTemasSelectos.services.UsuarioService;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author jimena
 */
@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    //Para encriptar la contraseña antes de guardarla
    private PasswordEncoder passwordEncoder;
    @Autowired // Inyecta la instancia de PasswordEncoder
    public UsuarioServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

     @Override
    public UsuarioModel guardarUsuario(UsuarioModel usuario) {
        // Encriptar la contraseña antes de guardarla
        String contraseñaEncriptada = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(contraseñaEncriptada);

        // Guardar el usuario en la base de datos
        return usuarioRepository.save(usuario);
    }
    
    // Método para generar una contraseña aleatoria
     @Override
    public String generarContrasenaAleatoria() {
        int longitud = 8; // Longitud de la contraseña
        String caracteresPermitidos = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder contrasena = new StringBuilder(longitud);
        
        for (int i = 0; i < longitud; i++) {
            int indice = random.nextInt(caracteresPermitidos.length());
            contrasena.append(caracteresPermitidos.charAt(indice));
        }
        return contrasena.toString();
    }
     @Override
    public List<UsuarioDTO> obtenerTodosLosUsuarios(){
        // Obtener todos los usuarios del repositorio
        List<UsuarioModel> usuarios = usuarioRepository.findAll();  
        
        // Mapear cada UsuarioModel a UsuarioDTO
        return usuarios.stream()
            .map(usuario -> new UsuarioDTO(
                usuario.getId(),
                usuario.getNombre()+" "+usuario.getApellidoPaterno()+" "+usuario.getApellidoMaterno(),
                usuario.getCorreo(), usuario.getIdTipoUsuario()
        ))
        .collect(Collectors.toList());
    }
     @Override
    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
    
    @Override
    public List<UsuarioDTO> obtenerEvaluadosDisponibles() {
        List<UsuarioModel> evaluados = usuarioRepository.findByIdTipoUsuario(3);
        return evaluados.stream().map(u -> {
            UsuarioDTO dto = new UsuarioDTO();
                dto.setId(u.getId());
                dto.setNombre(u.getNombre()+" "+u.getApellidoPaterno()+" "+u.getApellidoMaterno());
                dto.setCorreo(u.getCorreo());
                dto.setRol(3);
                return dto;
         }).toList();
    }
   
}
