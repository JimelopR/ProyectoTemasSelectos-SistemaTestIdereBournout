/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mac.ProyectoTemasSelectos.services;

import com.mac.ProyectoTemasSelectos.dtos.TestAsignadoDTO;
import com.mac.ProyectoTemasSelectos.dtos.TestRespuestaDTO;
import java.util.List;

/**
 *
 * @author jimena
 */
public interface TestAsignadoService {
    void asignarTest(TestAsignadoDTO dto);
    List<TestAsignadoDTO> obtenerTestsPendientesDelEvaluado(Long evaluadoId);
    void guardarRespuestasYEvaluar(TestRespuestaDTO dto);
}
