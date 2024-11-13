package modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Favoritos")
public class Favorito implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_favorito;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_receta", nullable = false)
    private Receta receta;

    @Column(name = "fecha_favorito", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaFavorito;

    @Column(nullable = false)
    private Boolean estado;
    
    public Integer getId_favorito() {
        return id_favorito;
    }

    public void setId_favorito(Integer id_favorito) {
        this.id_favorito = id_favorito;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public Date getFechaFavorito() {
        return fechaFavorito;
    }

    public void setFechaFavorito(Date fechaFavorito) {
        this.fechaFavorito = fechaFavorito;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

}
