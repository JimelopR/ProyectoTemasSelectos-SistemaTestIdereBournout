/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mac.ProyectoTemasSelectos.services;

import com.mac.ProyectoTemasSelectos.dtos.UsuarioDTO;
import com.mac.ProyectoTemasSelectos.models.UsuarioModel;
import java.util.List;

/**
 *
 * @author jimena
 */
public interface UsuarioService {
    UsuarioModel guardarUsuario(UsuarioModel usuario);
    String generarContrasenaAleatoria();
    List<UsuarioDTO> obtenerTodosLosUsuarios();
    boolean checkPassword(String rawPassword, String encodedPassword);
    List<UsuarioDTO> obtenerEvaluadosDisponibles();
}
