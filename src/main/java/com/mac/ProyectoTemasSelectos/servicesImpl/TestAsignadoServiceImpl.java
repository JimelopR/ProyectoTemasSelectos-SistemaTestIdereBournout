/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mac.ProyectoTemasSelectos.servicesImpl;

import com.mac.ProyectoTemasSelectos.dtos.TestAsignadoDTO;
import com.mac.ProyectoTemasSelectos.exceptions.ResourceNotFoundException;
import com.mac.ProyectoTemasSelectos.models.TestAsignadoModel;
import com.mac.ProyectoTemasSelectos.models.TestModel;
import com.mac.ProyectoTemasSelectos.models.UsuarioModel;
import com.mac.ProyectoTemasSelectos.repositories.TestAsignadoRepository;
import com.mac.ProyectoTemasSelectos.repositories.TestRepository;
import com.mac.ProyectoTemasSelectos.repositories.UsuarioRepository;
import com.mac.ProyectoTemasSelectos.services.TestAsignadoService;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jlopez
 */
@Service
public class TestAsignadoServiceImpl implements TestAsignadoService{

    @Autowired
    private TestAsignadoRepository testAsignadoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TestRepository testRepository;

    // Lógica para asignar un test al evaluado con el DTO
    @Transactional
    @Override
    public TestAsignadoModel asignarTest(TestAsignadoDTO dto) {
        // Verificar que el evaluador y el evaluado existen
        UsuarioModel evaluador = usuarioRepository.findById(dto.getEvaluadorId())
                .orElseThrow(() -> new ResourceNotFoundException("Evaluador no encontrado"));
        UsuarioModel evaluado = usuarioRepository.findById(dto.getEvaluadoId())
                .orElseThrow(() -> new ResourceNotFoundException("Evaluado no encontrado"));

        // Verificar que el test existe
        TestModel test = testRepository.findById(dto.getTestId())
                .orElseThrow(() -> new ResourceNotFoundException("Test no encontrado"));

        // Crear la asignación
        TestAsignadoModel testAsignado = new TestAsignadoModel();
        testAsignado.setEvaluador(evaluador);
        testAsignado.setEvaluado(evaluado);
        testAsignado.setTest(test);
        testAsignado.setCompletado(false);  // Inicialmente no está completado
        testAsignado.setFechaAsignacion(LocalDateTime.now());

        // Guardar la asignación en la base de datos
        return testAsignadoRepository.save(testAsignado);
    }
}

