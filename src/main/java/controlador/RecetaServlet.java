package controlador;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Receta;
import servicios.RecetaService;


import java.io.IOException;
import java.util.List;

@WebServlet("/RecetaServlet")
public class RecetaServlet extends HttpServlet {
    private RecetaService recetaService = new RecetaService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "list":
                    listarRecetas(request, response);
                    break;
                case "edit":
                    mostrarEditarReceta(request, response);
                    break;
                case "delete":
                    eliminarReceta(request, response);
                    break;
                default:
                    listarRecetas(request, response);
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
                agregarReceta(request, response);
            } else if ("update".equals(action)) {
                actualizarReceta(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    private void listarRecetas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Receta> recetas = recetaService.obtenerTodasRecetas();
        request.setAttribute("recetas", recetas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/recetas.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarEditarReceta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Receta receta = recetaService.buscarReceta(id);
        request.setAttribute("receta", receta);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/editar_receta.jsp");
        dispatcher.forward(request, response);
    }

    private void agregarReceta(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nombreReceta = request.getParameter("nombre_receta");
        String tipoCocina = request.getParameter("tipo_cocina");
        String ingredientes = request.getParameter("ingredientes");
        int tiempoPreparacion = Integer.parseInt(request.getParameter("tiempo_preparacion"));

        if (nombreReceta.isEmpty() || tipoCocina.isEmpty() || ingredientes.isEmpty() || tiempoPreparacion <= 0) {
            response.sendRedirect("error.jsp?error=Campos+obligatorios+faltantes");
            return;
        }

        Receta receta = new Receta();
        receta.setNombreReceta(nombreReceta);
        receta.setTipoCocina(tipoCocina);
        receta.setIngredientes(ingredientes);
        receta.setTiempoPreparacion(tiempoPreparacion);
        receta.setEstado(true);

        recetaService.crearReceta(receta);
        response.sendRedirect("RecetaServlet?action=list");
    }

    private void actualizarReceta(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Receta receta = recetaService.buscarReceta(id);

        if (receta != null) {
            receta.setNombreReceta(request.getParameter("nombre_receta"));
            receta.setTipoCocina(request.getParameter("tipo_cocina"));
            receta.setIngredientes(request.getParameter("ingredientes"));
            receta.setTiempoPreparacion(Integer.parseInt(request.getParameter("tiempo_preparacion")));
            recetaService.actualizarReceta(receta);
            response.sendRedirect("RecetaServlet?action=list");
        } else {
            response.sendRedirect("error.jsp?error=Receta+no+encontrada");
        }
    }

    private void eliminarReceta(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        recetaService.eliminarReceta(id);
        response.sendRedirect("RecetaServlet?action=list");
    }
}
