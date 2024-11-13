package com.Servlets;

import com.models.Usuario;
import com.utils.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;


import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/guardarUsuario")
public class GuardarUsuarioServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String email = request.getParameter("email");
            String clave = request.getParameter("clave");

            // Convertir fecha de registro
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaRegistro = formatter.parse(request.getParameter("fecha_registro"));

            String estado = request.getParameter("estado");
            Boolean accesoSistema = request.getParameter("accesoSistema") != null;

            // Procesar la imagen
            Part filePart = request.getPart("foto");
            byte[] foto = null;
            if (filePart != null && filePart.getSize() > 0) {
                InputStream inputStream = filePart.getInputStream();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, bytesRead);
                }
                foto = byteArrayOutputStream.toByteArray();
            }

            // Crear el objeto Usuario
            Usuario usuario = new Usuario(nombre, apellido, email, fechaRegistro, clave, estado, accesoSistema);
            usuario.setFoto(foto);

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.insertarUsuario(usuario);

            response.sendRedirect("Usuarios/success.jsp");  // Redirige a una página de éxito
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("Usuarios/error.jsp");  // Redirige a una página de error
        }
    }
}
