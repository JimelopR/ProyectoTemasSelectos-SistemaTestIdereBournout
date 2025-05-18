/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mac.ProyectoTemasSelectos.servicesImpl;

import com.mac.ProyectoTemasSelectos.dtos.RespuestaDTO;
import com.mac.ProyectoTemasSelectos.dtos.TestAsignadoDTO;
import com.mac.ProyectoTemasSelectos.dtos.TestRespuestaDTO;
import com.mac.ProyectoTemasSelectos.exceptions.ResourceNotFoundException;
import com.mac.ProyectoTemasSelectos.models.PreguntaModel;
import com.mac.ProyectoTemasSelectos.models.RespuestaModel;
import com.mac.ProyectoTemasSelectos.models.ResultadoTestModel;
import com.mac.ProyectoTemasSelectos.models.SubescalaModel;
import com.mac.ProyectoTemasSelectos.models.TestAsignadoModel;
import com.mac.ProyectoTemasSelectos.models.TestModel;
import com.mac.ProyectoTemasSelectos.models.UsuarioModel;
import com.mac.ProyectoTemasSelectos.repositories.PreguntaRepository;
import com.mac.ProyectoTemasSelectos.repositories.ResultadoTestRepository;
import com.mac.ProyectoTemasSelectos.repositories.TestAsignadoRepository;
import com.mac.ProyectoTemasSelectos.repositories.TestRepository;
import com.mac.ProyectoTemasSelectos.repositories.UsuarioRepository;
import com.mac.ProyectoTemasSelectos.services.TestAsignadoService;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jimena
 */
@Service
public class TestAsignadoServiceImpl implements TestAsignadoService{

    @Autowired
    private TestAsignadoRepository testAsignadoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TestRepository testRepository;
    
     @Autowired
    private ResultadoTestRepository resultadoTestRepository;
     
    @Autowired
    private PreguntaRepository preguntaRepository;

    @Override
    @Transactional
    public void asignarTest(TestAsignadoDTO dto) {
        UsuarioModel evaluador = usuarioRepository.findById(dto.getEvaluadorId())
            .orElseThrow(() -> new ResourceNotFoundException("Evaluador no encontrado"));

        UsuarioModel evaluado = usuarioRepository.findById(dto.getEvaluadoId())
            .orElseThrow(() -> new ResourceNotFoundException("Evaluado no encontrado"));

        TestModel test = testRepository.findById(dto.getTestId())
            .orElseThrow(() -> new ResourceNotFoundException("Test no encontrado"));

        TestAsignadoModel asignacion = new TestAsignadoModel();
        asignacion.setEvaluador(evaluador);
        asignacion.setEvaluado(evaluado);
        asignacion.setTest(test);
        asignacion.setCompletado(false);
        asignacion.setFechaAsignacion(LocalDateTime.now());
        asignacion.setFechaRespuesta(null);

        testAsignadoRepository.save(asignacion);
    }
    
    @Override
    public List<TestAsignadoDTO> obtenerTestsPendientesDelEvaluado(Long evaluadoId) {
    List<TestAsignadoModel> asignados = testAsignadoRepository.findByEvaluadoIdAndCompletadoFalse(evaluadoId);

        return asignados.stream().map(asignado -> {
            TestAsignadoDTO dto = new TestAsignadoDTO();
            dto.setEvaluadorId(asignado.getEvaluador().getId());
            dto.setEvaluadoId(asignado.getEvaluado().getId());
            dto.setTestId(asignado.getTest().getId());
            dto.setCompletado(asignado.isCompletado());
            dto.setFechaAsignacion(asignado.getFechaAsignacion());
            dto.setFechaRespuesta(asignado.getFechaRespuesta());
            dto.setEdadEvaluado(asignado.getEdadEvaluado());
            dto.setTrabaja(asignado.isTrabaja());
            dto.setGenero(asignado.getGenero());
        return dto;
        }).toList();
    }   
    
    @Override
    public void guardarRespuestasYEvaluar(TestRespuestaDTO dto) {
        TestAsignadoModel testAsignado = testAsignadoRepository.findById(dto.getTestAsignadoId())
            .orElseThrow(() -> new RuntimeException("Test asignado no encontrado"));

        Map<Long, Integer> mapaRespuestas = new HashMap<>();
        for (RespuestaDTO r : dto.getRespuestas()) {
            mapaRespuestas.put(r.getPreguntaId(), r.getValor());
        }
        
        
        TestModel test = testAsignado.getTest();
        ResultadoTestModel resultado = calcularResultado(test, mapaRespuestas);
        
        for (Map.Entry<Long, Integer> entry : mapaRespuestas.entrySet()) {
            Long preguntaId = entry.getKey();
            Integer valor = entry.getValue();

            PreguntaModel pregunta = test.getPreguntas().stream()
                .filter(p -> p.getId().equals(preguntaId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Pregunta no encontrada: " + preguntaId));

            RespuestaModel respuesta = new RespuestaModel();
            respuesta.setPregunta(pregunta);
            respuesta.setValor(valor);
            respuesta.setResultadoTest(resultado);

            resultado.getRespuestas().add(respuesta);
        }

        resultado.setTestAsignado(testAsignado);
        resultadoTestRepository.save(resultado);

        // Marcar como completado
        testAsignado.setCompletado(true);
        testAsignado.setFechaRespuesta(LocalDateTime.now());

        // Datos extra del evaluado
        if (dto.getEdadEvaluado() != null)
            testAsignado.setEdadEvaluado(dto.getEdadEvaluado());

        if (dto.getGenero() != null)
            testAsignado.setGenero(dto.getGenero());

        if (dto.getTrabaja() != null)
            testAsignado.setTrabaja(dto.getTrabaja());

        testAsignadoRepository.save(testAsignado);
    }

    private ResultadoTestModel calcularResultado(TestModel test, Map<Long, Integer> respuestas) {
        ResultadoTestModel resultado = new ResultadoTestModel();
        resultado.setTest(test);
        resultado.setFechaResultado(LocalDateTime.now());

        //Acumular puntajes por subescala
        Map<SubescalaModel, Integer> puntajes = new HashMap<>();

        for (PreguntaModel pregunta : test.getPreguntas()) {
            Integer valor = respuestas.get(pregunta.getId());
            if (valor == null) continue;
            
            
            // Recuperar la pregunta con su subescala asociada
            PreguntaModel preguntaConSubescala = preguntaRepository.findPreguntaWithSubescala(pregunta.getId());
            // Verificar si la subescala está presente
            if (preguntaConSubescala.getSubescala() != null) {
                SubescalaModel subescala = preguntaConSubescala.getSubescala();
                String grupo = subescala.getGrupo(); // A o B
                
                int current = puntajes.getOrDefault(subescala, 0);

                if ("B".equalsIgnoreCase(grupo)) {
                    current -= valor;
                    System.out.println("acumula: "+subescala+", -"+current);
                } else {
                    current += valor;
                    System.out.println("acumula: "+subescala+", +"+current);
                }
                
                System.out.println("acumula: "+subescala+", "+current);
                puntajes.put(subescala, current);
                    System.out.println("Subescala: " + subescala.getNombre() + ", Grupo: " + grupo);
            } else {
                System.out.println("La pregunta con ID " + pregunta.getId() + " no tiene una subescala asociada.");
            }   
        }

        StringBuilder interpretacion = new StringBuilder();

        //Procesar según el tipo de test
        if (test.getTipo().equalsIgnoreCase("IDARE")) {
            // Agrupar puntajes por tipo (Estado y Rasgo), y por grupo (A o B)
            int estadoA = 0, estadoB = 0, rasgoA = 0, rasgoB = 0;

            for (Map.Entry<SubescalaModel, Integer> entry : puntajes.entrySet()) {
                SubescalaModel sub = entry.getKey();
                int valor = entry.getValue();
                String nombre = sub.getNombre().toLowerCase();
                String grupo = sub.getGrupo(); // A o B

                if (nombre.contains("Estado")) {
                    if ("A".equalsIgnoreCase(grupo)) estadoA += valor;
                    else if ("B".equalsIgnoreCase(grupo)) estadoB += valor;
                } else if (nombre.contains("Rasgo")) {
                    if ("A".equalsIgnoreCase(grupo)) rasgoA += valor;
                    else if ("B".equalsIgnoreCase(grupo)) rasgoB += valor;
                }
            }

            // Calcular y evaluar los puntajes finales
            int totalEstado = (estadoA - estadoB) + 50;
            System.out.println("totalEstado: "+totalEstado);
            int totalRasgo = (rasgoA - rasgoB) + 35;
            System.out.println("totalRasg: "+totalRasgo);
            interpretacion.append("Ansiedad como estado: ").append(totalEstado).append(" → ");
            interpretacion.append(obtenerNivel(totalEstado, 30, 45)).append("\n");

            interpretacion.append("Ansiedad como rasgo: ").append(totalRasgo).append(" → ");
            interpretacion.append(obtenerNivel(totalRasgo, 30, 45)).append("\n");

        } else {
            // Para Burnout y otros tests: interpretar por subescala individual
            for (SubescalaModel subescala : puntajes.keySet()) {
                int total = puntajes.get(subescala);

                interpretacion.append(subescala.getNombre()).append(": ").append(total).append(" → ");

                if (total <= subescala.getRangoBajoMax()) {
                    interpretacion.append("BAJO");
                } else if (total <= subescala.getRangoMedioMax()) {
                    interpretacion.append("MEDIO");
                } else {
                    interpretacion.append("ALTO");
                }

                interpretacion.append("\n");
            }
        }

        resultado.setInterpretacion(interpretacion.toString());
        return resultado;
    }
    private String obtenerNivel(int total, int umbralMedio, int umbralAlto) {
        if (total < umbralMedio){ 
            return "BAJO";   
        }
        else if (total <= umbralAlto){ 
            return "MEDIO";
        }
        else{
            return "ALTO";
        }
    }
    public SubescalaModel obtenerSubescala(Long preguntaId) {
        PreguntaModel pregunta = preguntaRepository.findById(preguntaId)
                .orElseThrow(() -> new ResourceNotFoundException("Pregunta no encontrada"));
        return pregunta.getSubescala();
    }

}





