/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mac.ProyectoTemasSelectos.dtos;

import java.util.List;

/**
 *
 * @author jimena
 */
public class TestRespuestaDTO {
    private Long testAsignadoId;
    private List<RespuestaDTO> respuestas;

    // Datos demográficos del evaluado
    private Integer edadEvaluado;
    private Boolean trabaja;
    private String genero;

    public Long getTestAsignadoId() {
        return testAsignadoId;
    }

    public void setTestAsignadoId(Long testAsignadoId) {
        this.testAsignadoId = testAsignadoId;
    }

    public List<RespuestaDTO> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<RespuestaDTO> respuestas) {
        this.respuestas = respuestas;
    }

    public Integer getEdadEvaluado() {
        return edadEvaluado;
    }

    public void setEdadEvaluado(Integer edadEvaluado) {
        this.edadEvaluado = edadEvaluado;
    }

    public Boolean getTrabaja() {
        return trabaja;
    }

    public void setTrabaja(Boolean trabaja) {
        this.trabaja = trabaja;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

 
}

