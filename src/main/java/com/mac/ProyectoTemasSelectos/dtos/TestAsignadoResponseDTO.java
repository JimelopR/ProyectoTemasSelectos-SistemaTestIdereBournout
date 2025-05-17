/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mac.ProyectoTemasSelectos.dtos;

import java.time.LocalDateTime;

/**
 *
 * @author jlopez
 */
public class TestAsignadoResponseDTO {

    private Long id;
    private String evaluadorNombre;
    private String evaluadoNombre;
    private String testTitulo;
    private boolean completado;
    private LocalDateTime fechaAsignacion;
    private LocalDateTime fechaRespuesta;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEvaluadorNombre() {
        return evaluadorNombre;
    }

    public void setEvaluadorNombre(String evaluadorNombre) {
        this.evaluadorNombre = evaluadorNombre;
    }

    public String getEvaluadoNombre() {
        return evaluadoNombre;
    }

    public void setEvaluadoNombre(String evaluadoNombre) {
        this.evaluadoNombre = evaluadoNombre;
    }

    public String getTestTitulo() {
        return testTitulo;
    }

    public void setTestTitulo(String testTitulo) {
        this.testTitulo = testTitulo;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
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
}
