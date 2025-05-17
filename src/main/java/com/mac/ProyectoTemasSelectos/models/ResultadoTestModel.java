/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mac.ProyectoTemasSelectos.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author jimena
 */
@Entity
@Table(name = "resultados_test")
public class ResultadoTestModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private TestModel test;

    @ManyToOne
    private UsuarioModel evaluado;
    
    @Column(name = "fecha")
    private LocalDateTime fecha;
     
    @Column(name = "puntuacion_total")
    private Integer puntuacionTotal;

    @OneToMany(mappedBy = "resultadoTest", cascade = CascadeType.ALL)
    private List<RespuestaModel> respuestas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TestModel getTest() {
        return test;
    }

    public void setTest(TestModel test) {
        this.test = test;
    }

    public UsuarioModel getEvaluado() {
        return evaluado;
    }

    public void setEvaluado(UsuarioModel evaluado) {
        this.evaluado = evaluado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Integer getPuntuacionTotal() {
        return puntuacionTotal;
    }

    public void setPuntuacionTotal(Integer puntuacionTotal) {
        this.puntuacionTotal = puntuacionTotal;
    }

    public List<RespuestaModel> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<RespuestaModel> respuestas) {
        this.respuestas = respuestas;
    }


}

