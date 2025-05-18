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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @JoinColumn(name = "test_id")
    private TestModel test;
    
    @OneToOne
    @JoinColumn(name = "test_asignado_id")
    private TestAsignadoModel testAsignado;

    
    @Column(name = "fecha_resultado")
    private LocalDateTime fechaResultado;

    @Column(name = "interpretacion", columnDefinition = "TEXT")
    private String interpretacion;
    
    
    @OneToMany(mappedBy = "resultado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RespuestaModel> respuestas = new ArrayList<>();

    public TestAsignadoModel getTestAsignado() {
        return testAsignado;
    }

    public void setTestAsignado(TestAsignadoModel testAsignado) {
        this.testAsignado = testAsignado;
    }

    public LocalDateTime getFechaResultado() {
        return fechaResultado;
    }

    public void setFechaResultado(LocalDateTime fechaResultado) {
        this.fechaResultado = fechaResultado;
    }

    public String getInterpretacion() {
        return interpretacion;
    }

    public void setInterpretacion(String interpretacion) {
        this.interpretacion = interpretacion;
    }



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

    public List<RespuestaModel> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<RespuestaModel> respuestas) {
        this.respuestas = respuestas;
    }


}

