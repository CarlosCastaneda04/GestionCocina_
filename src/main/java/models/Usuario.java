// Clase Usuario en el paquete models
package models;

import java.util.Date;

public class Usuario {
    private int idUsuario;
    private String nombre;
    private String apellido;
    private String email;
    private Date fechaRegistro;
    private String clave;
    private byte[] foto;
    private int estado;
    private boolean accesoSistema;

    // Constructor vacío
    public Usuario() {}

    // Constructor con parámetros
    public Usuario(String nombre, String apellido, String email, Date fechaRegistro, String clave, int estado, boolean accesoSistema) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fechaRegistro = fechaRegistro;
        this.clave = clave;
        this.estado = estado;
        this.accesoSistema = accesoSistema;
    }

    // Getters y Setters
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Date getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Date fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }
    public byte[] getFoto() { return foto; }
    public void setFoto(byte[] foto) { this.foto = foto; }
    public int getEstado() { return estado; }
    public void setEstado(int estado) { this.estado = estado; }
    public boolean isAccesoSistema() { return accesoSistema; }
    public void setAccesoSistema(boolean accesoSistema) { this.accesoSistema = accesoSistema; }
}
