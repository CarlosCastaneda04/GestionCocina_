package servlets;

import models.Usuario;
import utils.DBConnection;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.sql.Timestamp;
import java.util.Date;

@WebServlet("/RegistroUsuarioServlet")
@MultipartConfig(maxFileSize = 16177215) // Tamaño máximo del archivo (aproximadamente 16MB)
public class RegistroUsuarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirigir al formulario de registro
        request.getRequestDispatcher("registroUsuario.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los datos del formulario
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String clave = request.getParameter("clave");

        // Validar y convertir el estado
        String estadoStr = request.getParameter("estado");
        int estado = 0;
        if (estadoStr != null && !estadoStr.isEmpty()) {
            try {
                estado = Integer.parseInt(estadoStr);
            } catch (NumberFormatException e) {
                response.sendRedirect("error.jsp?error=Estado no válido");
                return;
            }
        } else {
            response.sendRedirect("error.jsp?error=El estado es obligatorio");
            return;
        }

        // Procesar la imagen de perfil
        Part filePart = request.getPart("foto");
        byte[] foto = null;
        if (filePart != null && filePart.getSize() > 0) {
            foto = filePart.getInputStream().readAllBytes();
        }

        // Crear el usuario
        Usuario usuario = new Usuario(nombre, apellido, email, new Date(), clave, estado, true);
        usuario.setFoto(foto);

        // Guardar en la base de datos
        if (registrarUsuario(usuario)) {
            response.sendRedirect("registroExito.jsp");
        } else {
            response.sendRedirect("error.jsp?error=Error en el registro");
        }
    }

    private boolean registrarUsuario(Usuario usuario) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO Usuarios (nombre, apellido, email, fecha_registro, clave, foto, estado, acceso_sistema) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setString(3, usuario.getEmail());
            statement.setTimestamp(4, new Timestamp(usuario.getFechaRegistro().getTime()));
            statement.setString(5, usuario.getClave());
            statement.setBytes(6, usuario.getFoto());
            statement.setInt(7, usuario.getEstado());
            statement.setBoolean(8, usuario.isAccesoSistema());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
