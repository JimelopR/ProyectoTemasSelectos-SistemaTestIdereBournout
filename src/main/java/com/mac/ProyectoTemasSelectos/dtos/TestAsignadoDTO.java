/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mac.ProyectoTemasSelectos.dtos;

import java.time.LocalDateTime;

/**
 *
 * @author jimena
 */
public class TestAsignadoDTO {
    
    private Long evaluadorId;
    private Long evaluadoId;
    private Long testId;
    private boolean completado;
    private LocalDateTime fechaAsignacion;
    private LocalDateTime fechaRespuesta;
    private int edadEvaluado;
     private boolean trabaja;
    private String genero;

    public int getEdadEvaluado() {
        return edadEvaluado;
    }

    public void setEdadEvaluado(int edadEvaluado) {
        this.edadEvaluado = edadEvaluado;
    }

    public boolean isTrabaja() {
        return trabaja;
    }

    public void setTrabaja(boolean trabaja) {
        this.trabaja = trabaja;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
   
    public Long getEvaluadorId() {
        return evaluadorId;
    }

    public void setEvaluadorId(Long evaluadorId) {
        this.evaluadorId = evaluadorId;
    }

    public Long getEvaluadoId() {
        return evaluadoId;
    }

    public void setEvaluadoId(Long evaluadoId) {
        this.evaluadoId = evaluadoId;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
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
