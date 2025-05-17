/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mac.ProyectoTemasSelectos.controllers;

import com.mac.ProyectoTemasSelectos.dtos.TestFormatoDTO;
import com.mac.ProyectoTemasSelectos.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jlopez
 */
@RestController
@RequestMapping("/api/evaluado")
public class EvaluadoController {
    
     @Autowired
    private TestService testService;
    
     @GetMapping("/{id}/formato")
    public ResponseEntity<TestFormatoDTO> obtenerFormato(@PathVariable Long id) {
        TestFormatoDTO formato = testService.obtenerFormatoPorId(id);
        return ResponseEntity.ok(formato);
    }
    
}
