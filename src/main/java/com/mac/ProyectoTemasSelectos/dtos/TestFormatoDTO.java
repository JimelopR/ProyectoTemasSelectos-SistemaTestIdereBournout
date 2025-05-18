/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mac.ProyectoTemasSelectos.dtos;

import com.mac.ProyectoTemasSelectos.models.SubescalaModel;
import java.util.List;

/**
 *
 * @author jlopez
 */
public class TestFormatoDTO {

    private Long id;
    private String titulo;
    private String descripcion;
    private String tipo; // "BURNOUT" o "IDERE"
    private List<PreguntaDTO> preguntas;
    private List<SubescalaDTO> subescalas;
    private List<OpcionRespuestaDTO> opcionesRespuesta;

    public List<OpcionRespuestaDTO> getOpcionesRespuesta() {
        return opcionesRespuesta;
    }

    public void setOpcionesRespuesta(List<OpcionRespuestaDTO> opcionesRespuesta) {
        this.opcionesRespuesta = opcionesRespuesta;
    }

    public List<SubescalaDTO> getSubescalas() {
        return subescalas;
    }

    public void setSubescalas(List<SubescalaDTO> subescalas) {
        this.subescalas = subescalas;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<PreguntaDTO> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<PreguntaDTO> preguntas) {
        this.preguntas = preguntas;
    }

}
