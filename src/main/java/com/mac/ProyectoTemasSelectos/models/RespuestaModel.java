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
    private Integer valor; // por ejemplo, del 1 al 5

    @ManyToOne
    @JoinColumn(name = "pregunta_id")
    private PreguntaModel pregunta;

    @ManyToOne
    @JoinColumn(name = "resultado_test_id")
    private ResultadoTestModel resultadoTest;
    
    @ManyToOne
    @JoinColumn(name = "opcion_respuesta_id")
    private OpcionRespuestaModel opcionSeleccionada;

    public int getValor() {
        return opcionSeleccionada != null ? opcionSeleccionada.getValor() : 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setValor(Integer valor) {
        this.valor = valor;
    }
   

    public PreguntaModel getPregunta() {
        return pregunta;
    }

    public void setPregunta(PreguntaModel pregunta) {
        this.pregunta = pregunta;
    }

    public ResultadoTestModel getResultadoTest() {
        return resultadoTest;
    }

    public void setResultadoTest(ResultadoTestModel resultadoTest) {
        this.resultadoTest = resultadoTest;
    }
}
