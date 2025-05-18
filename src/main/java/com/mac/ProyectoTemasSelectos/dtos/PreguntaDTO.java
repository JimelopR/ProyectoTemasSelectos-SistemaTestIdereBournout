/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mac.ProyectoTemasSelectos.dtos;

/**
 *
 * @author jlopez
 */

public class PreguntaDTO {
    private Long id;
    private String texto;
    private Long idSubescala;

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

    public Long getIdSubescala() {
        return idSubescala;
    }

    public void setIdSubescala(Long idSubescala) {
        this.idSubescala = idSubescala;
    }
}
