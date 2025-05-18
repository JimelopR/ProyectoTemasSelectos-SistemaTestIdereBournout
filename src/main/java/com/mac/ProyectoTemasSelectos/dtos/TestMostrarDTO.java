/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mac.ProyectoTemasSelectos.dtos;

import java.time.LocalDateTime;

/**
 *
 * @author jimena
 */
public class TestMostrarDTO {
    private Long id;
    private String tipo;
    private String titulo;
    private String estado;
    private LocalDateTime fechaAsignacion;
    private LocalDateTime fechaRealizacion;
    private UsuarioDTO evaluado;
    private UsuarioDTO evaluador;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(LocalDateTime fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public LocalDateTime getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(LocalDateTime fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public UsuarioDTO getEvaluado() {
        return evaluado;
    }

    public void setEvaluado(UsuarioDTO evaluado) {
        this.evaluado = evaluado;
    }

    public UsuarioDTO getEvaluador() {
        return evaluador;
    }

    public void setEvaluador(UsuarioDTO evaluador) {
        this.evaluador = evaluador;
    }


    public static class UsuarioDTO {
        private Long id;
        private String nombreCompleto;
        private String correo;
        // Constructor, getters y setters

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNombreCompleto() {
            return nombreCompleto;
        }

        public void setNombreCompleto(String nombreCompleto) {
            this.nombreCompleto = nombreCompleto;
        }

        public String getCorreo() {
            return correo;
        }

        public void setCorreo(String correo) {
            this.correo = correo;
        }
    }
}

