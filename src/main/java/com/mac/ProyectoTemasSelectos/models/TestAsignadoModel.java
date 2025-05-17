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
import java.time.LocalDateTime;

/**
 *
 * @author jlopez
 */
@Entity
@Table(name = "test_asignados")
public class TestAsignadoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "evaluador_id")
    private UsuarioModel evaluador;  // Usuario que asigna el test

    @ManyToOne
    @JoinColumn(name = "evaluado_id")
    private UsuarioModel evaluado;  // Usuario que va a responder

    @ManyToOne
    @JoinColumn(name = "test_id")
    private TestModel test;  // El test que se asigna

    @Column(name = "completado")
    private boolean completado;  // Si el evaluado ya completó el test

    @Column(name = "fecha_asignacion")
    private LocalDateTime fechaAsignacion;  // Fecha de asignación del test

    @Column(name = "fecha_respuesta")
    private LocalDateTime fechaRespuesta;  // Fecha de respuesta

    @Column(name = "edad_evaluado")
    private int edadEvaluado;

    @Column(name = "trabaja")
    private boolean trabaja;

    @Column(name = "genero")
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioModel getEvaluador() {
        return evaluador;
    }

    public void setEvaluador(UsuarioModel evaluador) {
        this.evaluador = evaluador;
    }

    public UsuarioModel getEvaluado() {
        return evaluado;
    }

    public void setEvaluado(UsuarioModel evaluado) {
        this.evaluado = evaluado;
    }

    public TestModel getTest() {
        return test;
    }

    public void setTest(TestModel test) {
        this.test = test;
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
