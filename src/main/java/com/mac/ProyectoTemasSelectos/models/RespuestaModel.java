/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mac.ProyectoTemasSelectos.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author jimena
 */
@Entity
@Table(name = "respuestas")
public class RespuestaModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "valor")
    private int valor; // por ejemplo, del 1 al 5
    
     @ManyToOne
    @JoinColumn(name = "pregunta_id")
    private PreguntaModel pregunta;

    
    @ManyToOne
    @JoinColumn(name = "resultado_id")
    private ResultadoTestModel resultado;


    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public ResultadoTestModel getResultado() {
        return resultado;
    }

    public void setResultado(ResultadoTestModel resultado) {
        this.resultado = resultado;
    }

   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
   

    public PreguntaModel getPregunta() {
        return pregunta;
    }

    public void setPregunta(PreguntaModel pregunta) {
        this.pregunta = pregunta;
    }

    public ResultadoTestModel getResultadoTest() {
        return resultado;
    }

    public void setResultadoTest(ResultadoTestModel resultado) {
        this.resultado = resultado;
    }
}
