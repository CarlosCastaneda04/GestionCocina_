package servicios;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import modelo.Receta;
import java.util.List;

public class RecetaService {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SistemaCocinaPU");

    // Crear una nueva receta
    public void crearReceta(Receta receta) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(receta);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Obtener todas las recetas
    public List<Receta> obtenerTodasRecetas() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT r FROM Receta r", Receta.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Buscar una receta por ID
    public Receta buscarReceta(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Receta.class, id);
        } finally {
            em.close();
        }
    }

    // Actualizar receta
    public void actualizarReceta(Receta receta) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(receta);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Eliminar receta
    public void eliminarReceta(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Receta receta = em.find(Receta.class, id);
            if (receta != null) {
                em.getTransaction().begin();
                em.remove(receta);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }
}
