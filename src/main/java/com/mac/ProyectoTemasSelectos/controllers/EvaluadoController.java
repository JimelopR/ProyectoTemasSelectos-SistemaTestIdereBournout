/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mac.ProyectoTemasSelectos.controllers;

import com.mac.ProyectoTemasSelectos.dtos.TestAsignadoDTO;
import com.mac.ProyectoTemasSelectos.dtos.TestFormatoDTO;
import com.mac.ProyectoTemasSelectos.dtos.TestMostrarDTO;
import com.mac.ProyectoTemasSelectos.dtos.TestRespuestaDTO;
import com.mac.ProyectoTemasSelectos.services.TestAsignadoService;
import com.mac.ProyectoTemasSelectos.services.TestMostrarService;
import com.mac.ProyectoTemasSelectos.services.TestService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/evaluado")
public class EvaluadoController {
    
    @Autowired
    private TestService testService;
    @Autowired
    private TestAsignadoService testAsignadoService;
    @Autowired
    private TestMostrarService testMostrarService;
    
     @GetMapping("/{id}/formato")
    public ResponseEntity<TestFormatoDTO> obtenerFormato(@PathVariable Long id) {
        TestFormatoDTO formato = testService.obtenerFormatoPorId(id);
        return ResponseEntity.ok(formato);
    }
    
    @GetMapping("/{id}/tests-pendientes")
    public ResponseEntity<List<TestAsignadoDTO>> getTestsPendientesPorEvaluado(@PathVariable Long id) {
        List<TestAsignadoDTO> tests = testAsignadoService.obtenerTestsPendientesDelEvaluado(id);
        return ResponseEntity.ok(tests);
    }
    @PostMapping("/responder")
    public ResponseEntity<String> responderTest(@RequestBody TestRespuestaDTO dto) {
        testAsignadoService.guardarRespuestasYEvaluar(dto);
        return ResponseEntity.ok("Test respondido y evaluado correctamente.");
    }
    
    @GetMapping("/test-realizados/{idEvaluado}")
    public ResponseEntity<List<TestMostrarDTO>> obtenerTestsRealizadosPorEvaluado(@PathVariable Long idEvaluado) {
       
        List<TestMostrarDTO> resultado = testMostrarService.obtenerRealizadosPorEvaluado(idEvaluado);
        return ResponseEntity.ok(resultado);
    }
    
}
