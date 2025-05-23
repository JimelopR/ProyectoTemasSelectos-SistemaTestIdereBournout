/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mac.ProyectoTemasSelectos.dtos;

import com.mac.ProyectoTemasSelectos.models.OpcionRespuestaModel;

/**
 *
 * @author jimena
 */
public class OpcionRespuestaDTO {

    private Long id;
    private String texto;  // Ejemplo: "Nunca", "Una vez a la semana", etc.
    private int valor;     // Ejemplo: 0, 1, 2, ..., 5

    public OpcionRespuestaDTO(OpcionRespuestaModel opcion) {
        this.id = opcion.getId();
        this.texto= opcion.getTexto();
        this.valor=opcion.getValor();
    }

    // Getters y setters
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

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}

