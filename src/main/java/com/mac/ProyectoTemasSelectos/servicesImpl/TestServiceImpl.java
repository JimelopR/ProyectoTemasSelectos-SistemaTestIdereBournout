/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mac.ProyectoTemasSelectos.servicesImpl;

import com.mac.ProyectoTemasSelectos.dtos.OpcionRespuestaDTO;
import com.mac.ProyectoTemasSelectos.dtos.PreguntaDTO;
import com.mac.ProyectoTemasSelectos.dtos.SubescalaDTO;
import com.mac.ProyectoTemasSelectos.dtos.TestFormatoDTO;
import com.mac.ProyectoTemasSelectos.exceptions.ResourceNotFoundException;
import com.mac.ProyectoTemasSelectos.models.PreguntaModel;
import com.mac.ProyectoTemasSelectos.models.SubescalaModel;
import com.mac.ProyectoTemasSelectos.models.TestModel;
import com.mac.ProyectoTemasSelectos.repositories.PreguntaRepository;
import com.mac.ProyectoTemasSelectos.repositories.SubescalaRepository;
import com.mac.ProyectoTemasSelectos.repositories.TestRepository;
import com.mac.ProyectoTemasSelectos.services.TestService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jimena
 */
@Service
public class TestServiceImpl implements TestService {
    
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private SubescalaRepository subescalaRepository;
    @Autowired
    private PreguntaRepository preguntaRepository;

    @Transactional(readOnly = true)
    public List<TestFormatoDTO> obtenerTodos() {
        List<TestModel> tests = testRepository.findAll(); // Obtiene todos los test del repositorio
        return tests.stream()
        .map(test -> {
            TestFormatoDTO dto = new TestFormatoDTO();
            dto.setId(test.getId());
            dto.setTitulo(test.getTitulo());
            dto.setDescripcion(test.getDescripcion());
            dto.setTipo(test.getTipo());

            //Considerar obtener las preguntas y las subescalas
            return dto;
        })
        .collect(Collectors.toList()); // Transforma la lista de TestModel a TestFormatoDTO
}
    //Considerarlas para poder crear los test desde el front, necesitamos usar los DTO intermedios
    /*@Override
    public TestModel crearTest(TestModel test) {
        // Si quieres agregar validaciones, puedes hacerlo aquí
        return testRepository.save(test);
    }

    @Override
    public TestModel actualizarTest(Long id, TestModel testActualizado) {
        Optional<TestModel> optionalTest = testRepository.findById(id);
        if (optionalTest.isPresent()) {
            TestModel testExistente = optionalTest.get();
            testExistente.setTitulo(testActualizado.getTitulo());
            testExistente.setDescripcion(testActualizado.getDescripcion());
            testExistente.setTipo(testActualizado.getTipo());
            testExistente.setPreguntas(testActualizado.getPreguntas());
            return testRepository.save(testExistente);
        } else {
            throw new RuntimeException("Test no encontrado con ID: " + id);
        }
    }*/

    @Override
    public void eliminarTest(Long id) {
        // Verifica si el test existe
        TestModel test = testRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Test con ID " + id + " no encontrado"));

        // Elimina el test
        testRepository.delete(test);
    }

    @Override
    @Transactional(readOnly = true)
    public TestFormatoDTO obtenerFormatoPorId(Long id) {
        // Obtener el test por ID
        TestModel test = testRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Test no encontrado con id: " + id));

        // Crear el DTO para el formato del test
        TestFormatoDTO dto = new TestFormatoDTO();
        dto.setId(test.getId());
        dto.setTitulo(test.getTitulo());
        dto.setDescripcion(test.getDescripcion());
        dto.setTipo(test.getTipo());

        // Mapeamos las subescalas del test
        List<SubescalaDTO> subescalasDTO = test.getSubescalas().stream().map(subescala -> {
            SubescalaDTO subescalaDTO = new SubescalaDTO();
            subescalaDTO.setId(subescala.getId());
            subescalaDTO.setNombre(subescala.getNombre());
        
            // Incluimos los rangos en el DTO
            subescalaDTO.setRangoBajoMin(subescala.getRangoBajoMin());
            subescalaDTO.setRangoBajoMax(subescala.getRangoBajoMax());
            subescalaDTO.setRangoMedioMin(subescala.getRangoMedioMin());
            subescalaDTO.setRangoMedioMax(subescala.getRangoMedioMax());
            subescalaDTO.setRangoAltoMin(subescala.getRangoAltoMin());
            subescalaDTO.setRangoAltoMax(subescala.getRangoAltoMax());
            subescalaDTO.setDescripcion(subescala.getDescripcion());
        
            // Incluimos el grupo en el DTO
            subescalaDTO.setGrupo(subescala.getGrupo());

            
            return subescalaDTO;
        }).toList();
        
        // Asignamos las subescalas al DTO del test
        dto.setSubescalas(subescalasDTO);
        
        // Mapear preguntas con su subescala (por ID)
        List<PreguntaDTO> preguntasDTO = new ArrayList<>();
        for (SubescalaModel subescala : test.getSubescalas()) {
            for (PreguntaModel pregunta : subescala.getPreguntas()) {
                PreguntaDTO preguntaDTO = new PreguntaDTO();
                preguntaDTO.setId(pregunta.getId());
                preguntaDTO.setTexto(pregunta.getTexto());
                preguntaDTO.setIdSubescala(subescala.getId());
                preguntasDTO.add(preguntaDTO);
            }
        }
        dto.setPreguntas(preguntasDTO);
        
        // Mapear las opciones de respuesta
        List<OpcionRespuestaDTO> opcionesDTO = test.getOpcionesRespuesta().stream()
            .map(opcion -> new OpcionRespuestaDTO(opcion))
            .collect(Collectors.toList());

        // Asignar las opciones de respuesta al DTO
        dto.setOpcionesRespuesta(opcionesDTO);
        return dto;
    }
}


