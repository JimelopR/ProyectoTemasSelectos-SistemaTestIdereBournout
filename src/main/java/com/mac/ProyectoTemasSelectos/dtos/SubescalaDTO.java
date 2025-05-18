/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mac.ProyectoTemasSelectos.dtos;


/**
 *
 * @author jimena
 */
public class SubescalaDTO {
    
    private Long id;
    private String nombre;
    
    // Rango bajo
    private int rangoBajoMin;
    private int rangoBajoMax;

    // Rango medio
    private int rangoMedioMin;
    private int rangoMedioMax;

    // Rango alto
    private int rangoAltoMin;
    private int rangoAltoMax;
    
    private String descripcion;

    // Grupo (por ejemplo, "A", "B", o null)
    private String grupo;
    

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

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
