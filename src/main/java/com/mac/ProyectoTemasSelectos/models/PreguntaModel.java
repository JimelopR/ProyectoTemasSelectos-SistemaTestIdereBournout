/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mac.ProyectoTemasSelectos.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.List;

/**
 *
 *  @author jimena
 */
@Entity
@Table(name = "preguntas")
public class PreguntaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "texto", columnDefinition = "TEXT")
    private String texto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private TestModel test;
    
    @ManyToOne
    @JoinColumn(name = "subescala_id")
    private SubescalaModel subescala;
    
    @ManyToMany
    @JoinTable(
      name = "pregunta_opciones_respuesta", 
      joinColumns = @JoinColumn(name = "pregunta_id"), 
      inverseJoinColumns = @JoinColumn(name = "opcion_respuesta_id"))
    private List<OpcionRespuestaModel> opcionesRespuesta;
    
    
    // Agregar la anotación @JsonBackReference para la relación inversa
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference
    private UsuarioModel usuario;

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public List<OpcionRespuestaModel> getOpcionesRespuesta() {
        return opcionesRespuesta;
    }

    public void setOpcionesRespuesta(List<OpcionRespuestaModel> opcionesRespuesta) {
        this.opcionesRespuesta = opcionesRespuesta;
    }
    
    

    public SubescalaModel getSubescala() {
        return subescala;
    }

    public void setSubescala(SubescalaModel subescala) {
        this.subescala = subescala;
    }


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

    public TestModel getTest() {
        return test;
    }

    public void setTest(TestModel test) {
        this.test = test;
    }
}

