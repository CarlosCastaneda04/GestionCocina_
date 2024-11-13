package com.Servlets;

import com.models.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final String PERSISTENCE_UNIT_NAME = "GestionCocinaPU";
    private static EntityManagerFactory factory;

    @Override
    public void init() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    // Método para mostrar el formulario de login al acceder con GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response); // Redirige a login.jsp
    }

    // Método para procesar el login al enviar el formulario con POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String clave = request.getParameter("clave");

        EntityManager em = factory.createEntityManager();

        try {
            // Consultar usuario en la base de datos
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email AND u.clave = :clave AND u.acceso_sistema = true", Usuario.class);
            query.setParameter("email", email);
            query.setParameter("clave", clave);

            Usuario usuario = query.getResultStream().findFirst().orElse(null);

            if (usuario != null) {
                // Login exitoso
                response.getWriter().println("¡Inicio de sesión exitoso! Bienvenido, " + usuario.getNombre());
            } else {
                // Error en las credenciales
                response.getWriter().println("Error: Email o clave incorrecta, o el acceso al sistema está desactivado.");
            }
        } finally {
            em.close();
        }
    }

    @Override
    public void destroy() {
        factory.close();
    }
}

