/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mac.ProyectoTemasSelectos.servicesImpl;


import com.mac.ProyectoTemasSelectos.dtos.RespuestaResultadoDTO;
import com.mac.ProyectoTemasSelectos.dtos.ResultadoSubescalaDTO;
import com.mac.ProyectoTemasSelectos.dtos.ResultadoTestDTO;
import com.mac.ProyectoTemasSelectos.dtos.TestMostrarDTO;
import com.mac.ProyectoTemasSelectos.models.OpcionRespuestaModel;
import com.mac.ProyectoTemasSelectos.models.PreguntaModel;
import com.mac.ProyectoTemasSelectos.models.RespuestaModel;
import com.mac.ProyectoTemasSelectos.models.ResultadoTestModel;
import com.mac.ProyectoTemasSelectos.models.SubescalaModel;
import com.mac.ProyectoTemasSelectos.models.TestAsignadoModel;
import com.mac.ProyectoTemasSelectos.models.TestModel;
import com.mac.ProyectoTemasSelectos.repositories.ResultadoTestRepository;
import com.mac.ProyectoTemasSelectos.repositories.TestAsignadoRepository;
import com.mac.ProyectoTemasSelectos.services.TestMostrarService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jimena
 */
@Service
public class TestMostrarServiceImpl implements TestMostrarService{
    
    @Autowired
    private TestAsignadoRepository testAsignadoRepository;
    @Autowired
    private ResultadoTestRepository resultadoTestRepository;

    @Override
    public List<TestMostrarDTO> obtenerTodosParaAdmin() {
        List<TestAsignadoModel> tests = testAsignadoRepository.findAll();

        return tests.stream().map(test -> {
            TestMostrarDTO dto = new TestMostrarDTO();
            dto.setId(test.getId());
            dto.setTipo(test.getTest().getTipo());
            dto.setTitulo(test.getTest().getTitulo());
            dto.setEstado(test.getFechaRespuesta() == null ? "PENDIENTE" : "REALIZADO");
            dto.setFechaAsignacion(test.getFechaAsignacion());
            dto.setFechaRealizacion(test.getFechaRespuesta());

            TestMostrarDTO.UsuarioDTO evaluado = new TestMostrarDTO.UsuarioDTO();
            evaluado.setId(test.getEvaluado().getId());
            evaluado.setNombreCompleto(test.getEvaluado().getNombre()+" "+test.getEvaluado().getApellidoPaterno()+" "+test.getEvaluado().getApellidoMaterno());
            evaluado.setCorreo(test.getEvaluado().getCorreo());
            dto.setEvaluado(evaluado);

            TestMostrarDTO.UsuarioDTO evaluador = new TestMostrarDTO.UsuarioDTO();
            evaluador.setId(test.getEvaluador().getId());
            evaluador.setNombreCompleto(test.getEvaluador().getNombre()+" "+test.getEvaluador().getApellidoPaterno()+" "+test.getEvaluador().getApellidoMaterno());
            evaluador.setCorreo(test.getEvaluador().getCorreo());
            dto.setEvaluador(evaluador);

            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<TestMostrarDTO> obtenerParaEvaluador(Long evaluadorId) {
    List<TestAsignadoModel> tests = testAsignadoRepository.findByEvaluadorId(evaluadorId);

        return tests.stream().map(test -> {
            TestMostrarDTO dto = new TestMostrarDTO();
            dto.setId(test.getId());
            dto.setTipo(test.getTest().getTipo());
            dto.setTitulo(test.getTest().getTitulo());
            dto.setEstado(test.getFechaRespuesta() == null ? "PENDIENTE" : "REALIZADO");
            dto.setFechaAsignacion(test.getFechaAsignacion());
            dto.setFechaRealizacion(test.getFechaRespuesta());

            TestMostrarDTO.UsuarioDTO evaluado = new TestMostrarDTO.UsuarioDTO();
            evaluado.setId(test.getEvaluado().getId());
            evaluado.setNombreCompleto(test.getEvaluado().getNombre()+" "+test.getEvaluado().getApellidoPaterno()+" "+test.getEvaluado().getApellidoMaterno());
            evaluado.setCorreo(test.getEvaluado().getCorreo());
            dto.setEvaluado(evaluado);

            TestMostrarDTO.UsuarioDTO evaluador = new TestMostrarDTO.UsuarioDTO();
            evaluador.setId(test.getEvaluador().getId());
            evaluador.setNombreCompleto(test.getEvaluador().getNombre()+" "+test.getEvaluador().getApellidoPaterno()+" "+test.getEvaluador().getApellidoMaterno());
            evaluador.setCorreo(test.getEvaluador().getCorreo());
            dto.setEvaluador(evaluador);

            return dto;
        }).collect(Collectors.toList());
    }
    @Override
    public List<TestMostrarDTO> obtenerRealizadosPorEvaluado(Long evaluadoId) {
        List<TestAsignadoModel> tests = testAsignadoRepository.findByEvaluadoIdAndFechaRespuestaIsNotNull(evaluadoId);

        return tests.stream().map(test -> {
            TestMostrarDTO dto = new TestMostrarDTO();
            dto.setId(test.getId());
            dto.setTipo(test.getTest().getTipo());
            dto.setTitulo(test.getTest().getTitulo());
            dto.setEstado("REALIZADO");
            dto.setFechaAsignacion(test.getFechaAsignacion());
            dto.setFechaRealizacion(test.getFechaRespuesta());

            TestMostrarDTO.UsuarioDTO evaluado = new TestMostrarDTO.UsuarioDTO();
            evaluado.setId(test.getEvaluado().getId());
            evaluado.setNombreCompleto(test.getEvaluado().getNombre() + " " +
                                   test.getEvaluado().getApellidoPaterno() + " " +
                                   test.getEvaluado().getApellidoMaterno());
            evaluado.setCorreo(test.getEvaluado().getCorreo());
            dto.setEvaluado(evaluado);

            TestMostrarDTO.UsuarioDTO evaluador = new TestMostrarDTO.UsuarioDTO();
            evaluador.setId(test.getEvaluador().getId());
            evaluador.setNombreCompleto(test.getEvaluador().getNombre() + " " +
                                    test.getEvaluador().getApellidoPaterno() + " " +
                                    test.getEvaluador().getApellidoMaterno());
            evaluador.setCorreo(test.getEvaluador().getCorreo());
            dto.setEvaluador(evaluador);

            return dto;
        }).collect(Collectors.toList());
    } 
    @Override
    public ResultadoTestDTO obtenerResultado(Long  testAsignadoId) {

        ResultadoTestModel resultadoTest = resultadoTestRepository
        .findByTestAsignadoId(testAsignadoId);

        TestAsignadoModel testAsignado = resultadoTest.getTestAsignado();
        TestModel test = resultadoTest.getTest();

        ResultadoTestDTO dto = new ResultadoTestDTO();
        dto.setTestAsignadoId(testAsignadoId);
        dto.setTitulo(test.getTitulo());
        dto.setTipo(test.getTipo());
        dto.setFechaAsignacion(testAsignado.getFechaAsignacion());
        dto.setFechaRespuesta(testAsignado.getFechaRespuesta());

        // Evaluado
        dto.setEvaluadoNombreCompleto(testAsignado.getEvaluado().getNombre()+" "+testAsignado.getEvaluado().getApellidoPaterno()+" "+testAsignado.getEvaluado().getApellidoMaterno());

        // Evaluador
        dto.setEvaluadorNombreCompleto(testAsignado.getEvaluador().getNombre()+" "+testAsignado.getEvaluado().getApellidoPaterno()+" "+testAsignado.getEvaluado().getApellidoMaterno());

        // Datos extra del evaluado
        dto.setEdadEvaluado(testAsignado.getEdadEvaluado());
        dto.setGenero(testAsignado.getGenero());
        dto.setTrabaja(testAsignado.isTrabaja());

        // ==== RESPUESTAS ====
        List<OpcionRespuestaModel> opciones = test.getOpcionesRespuesta();
        List<RespuestaResultadoDTO> respuestas = resultadoTest.getRespuestas().stream().map(resp -> {
            PreguntaModel pregunta = resp.getPregunta();
            RespuestaResultadoDTO r = new RespuestaResultadoDTO();
            r.setPreguntaId(pregunta.getId());
            r.setPregunta(pregunta.getTexto());
            r.setValor(resp.getValor());

            // Buscar texto de la opción por valor y test
            String textoOpcion = opciones.stream()
                .filter(opt -> opt.getValor() == resp.getValor())
                .map(OpcionRespuestaModel::getTexto)
                .findFirst()
                .orElse("Desconocido");
                r.setTextoOpcion(textoOpcion);

            // Subescala
            r.setSubescalaNombre(pregunta.getSubescala().getNombre());

            return r;
        }).collect(Collectors.toList());

        dto.setRespuestas(respuestas);

        // ==== INTERPRETACIÓN ====
        Map<Long, Integer> sumas = new HashMap<>();
        for (RespuestaModel r : resultadoTest.getRespuestas()) {
            SubescalaModel sub = r.getPregunta().getSubescala();
            int actual = sumas.getOrDefault(sub.getId(), 0);

            int valor = r.getValor();
            String grupo = sub.getGrupo(); // puede ser "A" o "B"
            actual += "B".equalsIgnoreCase(grupo) ? -valor : valor;

            sumas.put(sub.getId(), actual);
        }

        List<ResultadoSubescalaDTO> resultadosSub = test.getSubescalas().stream().map(sub -> {
            ResultadoSubescalaDTO r = new ResultadoSubescalaDTO();
            r.setIdSubescala(sub.getId());
            r.setNombre(sub.getNombre());

            int puntaje = sumas.getOrDefault(sub.getId(), 0);
            r.setPuntuacion(puntaje);

            if (puntaje <= sub.getRangoBajoMax()) {
              r.setInterpretacion("BAJO");
            } else if (puntaje <= sub.getRangoMedioMax()) {
             r.setInterpretacion("MEDIO");
            } else {
              r.setInterpretacion("ALTO");
            }

            return r;
        }).collect(Collectors.toList());

        dto.setResultadosSubescala(resultadosSub);

        return dto;
    }   
}