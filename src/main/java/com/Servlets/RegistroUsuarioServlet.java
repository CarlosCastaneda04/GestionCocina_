package com.Servlets;

import com.models.Usuario;
import com.utils.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;

@WebServlet("/RegistroUsuarioServlet")
@MultipartConfig(maxFileSize = 16177215) // Tamaño máximo de archivo de 16MB
public class RegistroUsuarioServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String clave = request.getParameter("clave");
        int estado = Integer.parseInt(request.getParameter("estado"));

        Part filePart = request.getPart("foto");
        byte[] foto = null;
        if (filePart != null && filePart.getSize() > 0) {
            try (InputStream inputStream = filePart.getInputStream()) {
                foto = inputStream.readAllBytes();
            }
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email);
        usuario.setClave(clave);
        usuario.setEstado(estado);
        usuario.setFoto(foto);

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        try {
            usuarioDAO.registrarUsuario(usuario);
            response.sendRedirect("Usuarios/registroExito.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("Usuarios/registroUsuario.jsp?error=Error en el registro");
        }
    }
}
