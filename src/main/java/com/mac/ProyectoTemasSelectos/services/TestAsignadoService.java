/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mac.ProyectoTemasSelectos.services;

import com.mac.ProyectoTemasSelectos.dtos.TestAsignadoDTO;
import com.mac.ProyectoTemasSelectos.models.TestAsignadoModel;

/**
 *
 * @author jlopez
 */
public interface TestAsignadoService {
    TestAsignadoModel asignarTest(TestAsignadoDTO dto);
}
