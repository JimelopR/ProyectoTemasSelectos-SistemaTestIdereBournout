/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mac.ProyectoTemasSelectos.models;

/**
 *
 * @author jimena
 */
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "correo", length = 100,unique = true)
    private String correo;

    @Column(name = "id_tipo_usuario", length = 100)
    private int idTipoUsuario ;
    
    @Column(name = "password", length = 255)
    private String password; 

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro; 
    
    @Column(name = "apellido_paterno", length = 100)
    private String apellidoPaterno; 

    @Column(name = "apellido_materno", length = 100)
    private String apellidoMaterno; 
  
    // Método para inicializar fechaRegistro en el momento de la creación del usuario
    @PrePersist
    public void prePersist() {
        if (fechaRegistro == null) {
            fechaRegistro = LocalDateTime.now(); // Asignar la fecha y hora actuales
        }
    }
    
   
    
    public UsuarioModel() {
    }
    // Constructor con parámetros
    public UsuarioModel(String correo, String password) {
        this.correo = correo;
        this.password = password;
    }
    // Getters y Setters
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }
    
    
   
    
}

