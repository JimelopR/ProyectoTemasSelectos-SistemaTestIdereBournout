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
import jakarta.persistence.Table;
import java.util.List;

/**
 *
 * @author jimena
 */
@Entity
@Table(name = "subescalas")
public class SubescalaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombre", length = 100)
    private String nombre; // Ej: "Cansancio emocional", "Ansiedad como estado"

    @ManyToOne
    @JoinColumn(name = "test_id")
    private TestModel test;

    // Rango bajo, medio, alto
    private int rangoBajoMin;
    private int rangoBajoMax;
    
    private int rangoMedioMin;
    private int rangoMedioMax;
 
    private int rangoAltoMin;
    private int rangoAltoMax;
    
    private String grupo; // valores como "A" o "B" para IDARE, o null para otros tests

    @OneToMany(mappedBy = "subescala", cascade = CascadeType.ALL)
    private List<PreguntaModel> preguntas;
    
    
    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TestModel getTest() {
        return test;
    }

    public void setTest(TestModel test) {
        this.test = test;
    }

    public int getRangoBajoMin() {
        return rangoBajoMin;
    }

    public void setRangoBajoMin(int rangoBajoMin) {
        this.rangoBajoMin = rangoBajoMin;
    }

    public int getRangoBajoMax() {
        return rangoBajoMax;
    }

    public void setRangoBajoMax(int rangoBajoMax) {
        this.rangoBajoMax = rangoBajoMax;
    }

    public int getRangoMedioMin() {
        return rangoMedioMin;
    }

    public void setRangoMedioMin(int rangoMedioMin) {
        this.rangoMedioMin = rangoMedioMin;
    }

    public int getRangoMedioMax() {
        return rangoMedioMax;
    }

    public void setRangoMedioMax(int rangoMedioMax) {
        this.rangoMedioMax = rangoMedioMax;
    }

    public int getRangoAltoMin() {
        return rangoAltoMin;
    }

    public void setRangoAltoMin(int rangoAltoMin) {
        this.rangoAltoMin = rangoAltoMin;
    }

    public int getRangoAltoMax() {
        return rangoAltoMax;
    }

    public void setRangoAltoMax(int rangoAltoMax) {
        this.rangoAltoMax = rangoAltoMax;
    }

    public List<PreguntaModel> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<PreguntaModel> preguntas) {
        this.preguntas = preguntas;
    }
}
