package controlador;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Favorito;
import modelo.Usuario;
import modelo.Receta;
import servicios.FavoritoService;
import servicios.UsuarioService;
import servicios.RecetaService;

import java.io.IOException;
import java.util.List;

@WebServlet("/FavoritoServlet")
public class FavoritoServlet extends HttpServlet {
    private FavoritoService favoritoService = new FavoritoService();
    private UsuarioService usuarioService = new UsuarioService();
    private RecetaService recetaService = new RecetaService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "list":
                    listarFavoritos(request, response);
                    break;
                case "delete":
                    eliminarFavorito(request, response);
                    break;
                default:
                    listarFavoritos(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if ("add".equals(request.getParameter("action"))) {
                agregarFavorito(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    private void listarFavoritos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int usuarioId = Integer.parseInt(request.getParameter("usuarioId"));
        Usuario usuario = usuarioService.buscarUsuario(usuarioId);
        List<Favorito> favoritos = favoritoService.obtenerFavoritosPorUsuario(usuario);
        request.setAttribute("favoritos", favoritos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/favoritos.jsp");
        dispatcher.forward(request, response);
    }

    private void agregarFavorito(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int usuarioId = Integer.parseInt(request.getParameter("usuarioId"));
        int recetaId = Integer.parseInt(request.getParameter("recetaId"));
        
        Usuario usuario = usuarioService.buscarUsuario(usuarioId);
        Receta receta = recetaService.buscarReceta(recetaId);

        if (usuario != null && receta != null) {
            Favorito favorito = new Favorito();
            favorito.setUsuario(usuario);
            favorito.setReceta(receta);
            favoritoService.crearFavorito(favorito);
            response.sendRedirect("FavoritoServlet?action=list&usuarioId=" + usuarioId);
        } else {
            response.sendRedirect("error.jsp?error=Usuario+o+Receta+no+encontrado");
        }
    }

    private void eliminarFavorito(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int favoritoId = Integer.parseInt(request.getParameter("id"));
        favoritoService.eliminarFavorito(favoritoId);
        response.sendRedirect("FavoritoServlet?action=list&usuarioId=" + request.getParameter("usuarioId"));
    }
}
