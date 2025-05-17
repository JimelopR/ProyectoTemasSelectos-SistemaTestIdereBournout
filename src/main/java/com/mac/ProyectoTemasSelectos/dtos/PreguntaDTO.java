/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mac.ProyectoTemasSelectos.dtos;

import java.util.List;

/**
 *
 * @author jlopez
 */

public class PreguntaDTO {
    private Long id;
    private String texto;
    private SubescalaDTO subescala;
    private List<OpcionRespuestaDTO> opciones;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public SubescalaDTO getSubescala() {
        return subescala;
    }

    public void setSubescala(SubescalaDTO subescala) {
        this.subescala = subescala;
    }

    public List<OpcionRespuestaDTO> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<OpcionRespuestaDTO> opciones) {
        this.opciones = opciones;
    }
}
