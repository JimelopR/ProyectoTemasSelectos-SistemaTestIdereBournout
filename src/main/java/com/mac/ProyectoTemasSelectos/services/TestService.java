/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mac.ProyectoTemasSelectos.services;

import com.mac.ProyectoTemasSelectos.dtos.TestFormatoDTO;
import java.util.List;

/**
 *
 *  @author jimena
 */

public interface TestService {
    List<TestFormatoDTO> obtenerTodos(); // solo info general
    //TestFormatoDTO crearTest(CreateTestDTO dto);
    //TestFormatoDTO actualizarTest(Long id, UpdateTestFormatoDTO dto);
    void eliminarTest(Long id);

    // NUEVO: Formato completo
    TestFormatoDTO obtenerFormatoPorId(Long id);
}
