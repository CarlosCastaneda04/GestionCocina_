package servicios;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import modelo.Usuario;
import java.util.List;

public class UsuarioService {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SistemaCocinaPU");

    // Crear un nuevo usuario
    public void crearUsuario(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Obtener todos los usuarios
    public List<Usuario> obtenerTodosUsuarios() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Buscar un usuario por ID
    public Usuario buscarUsuario(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    // Actualizar usuario
    public void actualizarUsuario(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(usuario);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Eliminar usuario
    public void eliminarUsuario(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Usuario usuario = em.find(Usuario.class, id);
            if (usuario != null) {
                em.getTransaction().begin();
                em.remove(usuario);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }
}

