package com.utils;

import com.models.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class UsuarioDAO {

    private static final String PERSISTENCE_UNIT_NAME = "GestionCocinaPU";
    private static EntityManagerFactory factory;

    public UsuarioDAO() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public Usuario obtenerUsuarioPorEmailYClave(String email, String clave) {
        EntityManager em = factory.createEntityManager();
        Usuario usuario = null;
        
        try {
            TypedQuery<Usuario> query = em.createQuery(
                "SELECT u FROM Usuarios u WHERE u.email = :email AND u.clave = :clave", Usuario.class);
            query.setParameter("email", email);
            query.setParameter("clave", clave);
            usuario = query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Usuario no encontrado o credenciales incorrectas");
        } finally {
            em.close();
        }
        
        return usuario;
    }
}