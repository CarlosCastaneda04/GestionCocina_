package modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Recetas")
public class Receta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_receta;

    @Column(name = "nombre_receta", nullable = false, length = 100)
    private String nombreReceta;

    @Column(name = "tipo_cocina", nullable = false, length = 50)
    private String tipoCocina;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String ingredientes;

    @Column(name = "tiempo_preparacion", nullable = false)
    private Integer tiempoPreparacion;

    @Lob
    private byte[] imagen;

    @Column(nullable = false)
    private Boolean estado;

public Integer getId_receta() {
        return id_receta;
    }

    public void setId_receta(Integer id_receta) {
        this.id_receta = id_receta;
    }

    public String getNombreReceta() {
        return nombreReceta;
    }

    public void setNombreReceta(String nombreReceta) {
        this.nombreReceta = nombreReceta;
    }

    public String getTipoCocina() {
        return tipoCocina;
    }

    public void setTipoCocina(String tipoCocina) {
        this.tipoCocina = tipoCocina;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public Integer getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setTiempoPreparacion(Integer tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}

