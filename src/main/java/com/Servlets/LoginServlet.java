package com.Servlets;

import com.models.Usuario;
import com.utils.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros del formulario
        String email = request.getParameter("email");
        String clave = request.getParameter("clave");

        // Instanciar el DAO de Usuario
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        // Buscar el usuario en la base de datos
        Usuario usuario = usuarioDAO.obtenerUsuarioPorEmailYClave(email, clave);

        if (usuario != null) {
            // Credenciales correctas, crear sesión y redirigir al inicio
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);
            response.sendRedirect("home.jsp"); // Redirige a la página de inicio
        } else {
            // Credenciales incorrectas, redirigir al login con un mensaje de error
            response.sendRedirect("Usuarios/login.jsp?error=1");
        }
    }
}
