/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mac.ProyectoTemasSelectos.controllers;

import com.mac.ProyectoTemasSelectos.dtos.TestAsignadoDTO;
import com.mac.ProyectoTemasSelectos.dtos.TestMostrarDTO;
import com.mac.ProyectoTemasSelectos.dtos.UsuarioDTO;
import com.mac.ProyectoTemasSelectos.services.TestAsignadoService;
import com.mac.ProyectoTemasSelectos.services.TestMostrarService;
import com.mac.ProyectoTemasSelectos.services.UsuarioService;
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
@RequestMapping("/api/evaluador")
public class EvaluadorController {
    
    @Autowired
    private TestAsignadoService testAsignadoService;
    @Autowired
    private TestMostrarService testMostrarService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    // Endpoint para asignar un test usando el DTO
    @PostMapping("/asignarTest")
    public ResponseEntity<?> asignarTest(@RequestBody TestAsignadoDTO dto) {
        testAsignadoService.asignarTest(dto);
        return ResponseEntity.ok("Test asignado correctamente");
}
    /*@GetMapping("obtenerTest/{id}")
    public ResponseEntity<TestAsignadoResponseDTO> obtenerTestAsignado(@PathVariable Long id) {
        TestAsignadoModel testAsignado = testAsignadoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Test asignado no encontrado"));

        TestAsignadoResponseDTO dto = new TestAsignadoResponseDTO();
        dto.setId(testAsignado.getId());
        dto.setEvaluadorNombre(testAsignado.getEvaluador().getNombre());
        dto.setEvaluadoNombre(testAsignado.getEvaluado().getNombre());
        dto.setTestTitulo(testAsignado.getTest().getTitulo());
        dto.setCompletado(testAsignado.isCompletado());
        dto.setFechaAsignacion(testAsignado.getFechaAsignacion());
        dto.setFechaRespuesta(testAsignado.getFechaRespuesta());

        return ResponseEntity.ok(dto);
    }*/
    @GetMapping("/obtenerEvaluados")
    public ResponseEntity<List<UsuarioDTO>> getEvaluadosDisponibles() {
        return ResponseEntity.ok(usuarioService.obtenerEvaluadosDisponibles());
    }
    
    
    @GetMapping("/test-existentes/{idEvaluador}")
    public ResponseEntity<List<TestMostrarDTO>> obtenerTestsPorEvaluador(@PathVariable Long idEvaluador) {
        List<TestMostrarDTO> resultado = testMostrarService.obtenerParaEvaluador(idEvaluador);
        return ResponseEntity.ok(resultado);   
    }
}
