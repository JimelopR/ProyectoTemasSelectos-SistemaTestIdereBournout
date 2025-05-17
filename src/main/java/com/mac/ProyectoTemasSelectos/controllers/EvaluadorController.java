/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mac.ProyectoTemasSelectos.controllers;

import com.mac.ProyectoTemasSelectos.dtos.TestAsignadoDTO;
import com.mac.ProyectoTemasSelectos.dtos.TestAsignadoResponseDTO;
import com.mac.ProyectoTemasSelectos.exceptions.ResourceNotFoundException;
import com.mac.ProyectoTemasSelectos.models.TestAsignadoModel;
import com.mac.ProyectoTemasSelectos.repositories.TestAsignadoRepository;
import com.mac.ProyectoTemasSelectos.services.TestAsignadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jlopez
 */
@RestController
@RequestMapping("/api/evaluador")
public class EvaluadorController {
    
    @Autowired
    private TestAsignadoService testAsignadoService;
    
    @Autowired
    private TestAsignadoRepository testAsignadoRepository;
    
    // Endpoint para asignar un test usando el DTO
    @PostMapping("/asignarTest")
    public ResponseEntity<TestAsignadoModel> asignarTest(@RequestBody TestAsignadoDTO dto) {
        TestAsignadoModel testAsignado = testAsignadoService.asignarTest(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(testAsignado);
    }
    @GetMapping("obtenerTest/{id}")
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
    }

}
