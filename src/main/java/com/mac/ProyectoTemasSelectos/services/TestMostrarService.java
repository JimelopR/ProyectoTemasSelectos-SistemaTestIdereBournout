/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mac.ProyectoTemasSelectos.services;

import com.mac.ProyectoTemasSelectos.dtos.ResultadoTestDTO;
import com.mac.ProyectoTemasSelectos.dtos.TestMostrarDTO;
import java.util.List;

/**
 *
 * @author jimena
 */
public interface TestMostrarService {
    List<TestMostrarDTO> obtenerTodosParaAdmin();
    List<TestMostrarDTO> obtenerParaEvaluador(Long evaluadorId);
    List<TestMostrarDTO> obtenerRealizadosPorEvaluado(Long idEvaluado);
    ResultadoTestDTO obtenerResultado(Long idTestAsignado);
    
}
