/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mac.ProyectoTemasSelectos.dtos;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author jimena
 */
public class ResultadoTestDTO {
    private Long testAsignadoId;
    private String titulo;
    private String tipo;
    private LocalDateTime fechaAsignacion;
    private LocalDateTime fechaRespuesta;

    private String evaluadoNombreCompleto;
    private String evaluadorNombreCompleto;

    private Integer edadEvaluado;
    private String genero;
    private Boolean trabaja;

    private List<RespuestaResultadoDTO> respuestas;
    private List<ResultadoSubescalaDTO> resultadosSubescala;

    public Long getTestAsignadoId() {
        return testAsignadoId;
    }

    public void setTestAsignadoId(Long testAsignadoId) {
        this.testAsignadoId = testAsignadoId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(LocalDateTime fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public LocalDateTime getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(LocalDateTime fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    public String getEvaluadoNombreCompleto() {
        return evaluadoNombreCompleto;
    }

    public void setEvaluadoNombreCompleto(String evaluadoNombreCompleto) {
        this.evaluadoNombreCompleto = evaluadoNombreCompleto;
    }

    public String getEvaluadorNombreCompleto() {
        return evaluadorNombreCompleto;
    }

    public void setEvaluadorNombreCompleto(String evaluadorNombreCompleto) {
        this.evaluadorNombreCompleto = evaluadorNombreCompleto;
    }

    public Integer getEdadEvaluado() {
        return edadEvaluado;
    }

    public void setEdadEvaluado(Integer edadEvaluado) {
        this.edadEvaluado = edadEvaluado;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Boolean getTrabaja() {
        return trabaja;
    }

    public void setTrabaja(Boolean trabaja) {
        this.trabaja = trabaja;
    }

    public List<RespuestaResultadoDTO> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<RespuestaResultadoDTO> respuestas) {
        this.respuestas = respuestas;
    }

    public List<ResultadoSubescalaDTO> getResultadosSubescala() {
        return resultadosSubescala;
    }

    public void setResultadosSubescala(List<ResultadoSubescalaDTO> resultadosSubescala) {
        this.resultadosSubescala = resultadosSubescala;
    }
    
}
