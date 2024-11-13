package com.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_usuario;

    private String nombre;
    private String apellido;
    private String email;

    @Temporal(TemporalType.DATE)
    private Date fecha_registro;

    private String clave;
    private byte[] foto;  // Opcional: foto en formato binario
    private String estado;
    private Boolean accesoSistema;

    // Constructor vacío (necesario para JPA)
    public Usuario() {
    }

    // Constructor completo
    public Usuario(String nombre, String apellido, String email, Date fecha_registro, String clave, String estado, Boolean accesoSistema) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fecha_registro = fecha_registro;
        this.clave = clave;
        this.estado = estado;
        this.accesoSistema = accesoSistema;
    }

    // Getters y Setters

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Boolean getAccesoSistema() {
        return accesoSistema;
    }

    public void setAccesoSistema(Boolean accesoSistema) {
        this.accesoSistema = accesoSistema;
    }
}

