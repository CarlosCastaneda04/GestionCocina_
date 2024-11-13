package com.utils;

import com.models.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class UsuarioDAO {
    private static final String PERSISTENCE_UNIT_NAME = "GestionCocinaPU";
    private static EntityManagerFactory factory;

    public UsuarioDAO() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public void insertarUsuario(Usuario usuario) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
        em.close();
    }
    
    public Usuario obtenerUsuarioPorEmailYClave(String email, String clave) {
        EntityManager em = factory.createEntityManager();
        Usuario usuario = null;
        try {
            Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email AND u.clave = :clave");
            query.setParameter("email", email);
            query.setParameter("clave", clave);
            usuario = (Usuario) query.getSingleResult();
        } catch (NoResultException e) {
            // No se encontró el usuario
            System.out.println("Usuario no encontrado");
        } finally {
            em.close();
        }
        return usuario;
    }
}

