package servicios;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import modelo.Favorito;
import modelo.Usuario;
import modelo.Receta;
import java.util.List;

public class FavoritoService {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SistemaCocinaPU");

    // Crear un nuevo favorito
    public void crearFavorito(Favorito favorito) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(favorito);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Obtener todos los favoritos de un usuario
    public List<Favorito> obtenerFavoritosPorUsuario(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT f FROM Favorito f WHERE f.usuario = :usuario", Favorito.class)
                     .setParameter("usuario", usuario)
                     .getResultList();
        } finally {
            em.close();
        }
    }

    // Eliminar un favorito
    public void eliminarFavorito(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Favorito favorito = em.find(Favorito.class, id);
            if (favorito != null) {
                em.getTransaction().begin();
                em.remove(favorito);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }

    // Obtener recetas más populares
    public List<Object[]> obtenerRecetasPopulares() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                "SELECT f.receta.nombreReceta, COUNT(f) as popularidad " +
                "FROM Favorito f GROUP BY f.receta ORDER BY popularidad DESC", Object[].class)
                .getResultList();
        } finally {
            em.close();
        }
    }
}

