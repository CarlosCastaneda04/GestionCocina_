package com.Servlets;

import com.models.Usuario;
import com.utils.UsuarioDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Usuarios/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String clave = request.getParameter("clave");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionCocinaPU");
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email AND u.clave = :clave", Usuario.class);
            query.setParameter("email", email);
            query.setParameter("clave", clave);

            Usuario usuario = query.getSingleResult();

            if (usuario != null) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuario);
                response.sendRedirect("Usuarios/home.jsp");
            } else {
                response.sendRedirect("Usuarios/login.jsp?error=1");
            }
        } catch (NoResultException e) {
            response.sendRedirect("Usuarios/login.jsp?error=1");
        } finally {
            em.close();
            emf.close();
        }
    }
}
