package controlador;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Usuario;
import servicios.UsuarioService;

import java.io.IOException;
import java.util.List;

@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {
    private UsuarioService usuarioService = new UsuarioService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "list":
                    listarUsuarios(request, response);
                    break;
                case "edit":
                    mostrarEditarUsuario(request, response);
                    break;
                case "delete":
                    eliminarUsuario(request, response);
                    break;
                default:
                    listarUsuarios(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("add".equals(action)) {
                agregarUsuario(request, response);
            } else if ("update".equals(action)) {
                actualizarUsuario(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Usuario> usuarios = usuarioService.obtenerTodosUsuarios();
        request.setAttribute("usuarios", usuarios);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/usuarios.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarEditarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Usuario usuario = usuarioService.buscarUsuario(id);
        request.setAttribute("usuario", usuario);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/editar_usuario.jsp");
        dispatcher.forward(request, response);
    }

    private void agregarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String clave = request.getParameter("clave");

        if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || clave.isEmpty()) {
            response.sendRedirect("error.jsp?error=Campos+obligatorios+faltantes");
            return;
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email);
        usuario.setClave(clave); // Asegúrate de encriptarla si es necesario
        usuario.setEstado(Usuario.EstadoUsuario.ACTIVO);

        usuarioService.crearUsuario(usuario);
        response.sendRedirect("UsuarioServlet?action=list");
    }

    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Usuario usuario = usuarioService.buscarUsuario(id);

        if (usuario != null) {
            usuario.setNombre(request.getParameter("nombre"));
            usuario.setApellido(request.getParameter("apellido"));
            usuario.setEmail(request.getParameter("email"));
            usuarioService.actualizarUsuario(usuario);
            response.sendRedirect("UsuarioServlet?action=list");
        } else {
            response.sendRedirect("error.jsp?error=Usuario+no+encontrado");
        }
    }

    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        usuarioService.eliminarUsuario(id);
        response.sendRedirect("UsuarioServlet?action=list");
    }
}
