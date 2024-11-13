<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestión de Recetas</title>
</head>
<body>
    <h1>Lista de Recetas</h1>
    <a href="nueva_receta.jsp">Agregar Nueva Receta</a>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nombre de la Receta</th>
            <th>Tipo de Cocina</th>
            <th>Tiempo de Preparación (min)</th>
            <th>Estado</th>
            <th>Acciones</th>
        </tr>
        <c:forEach var="receta" items="${recetas}">
            <tr>
                <td>${receta.id_receta}</td>
                <td>${receta.nombreReceta}</td>
                <td>${receta.tipoCocina}</td>
                <td>${receta.tiempoPreparacion}</td>
                <td>${receta.estado ? "Activa" : "Inactiva"}</td>
                <td>
                    <a href="RecetaServlet?action=edit&id=${receta.id_receta}">Editar</a>
                    <a href="RecetaServlet?action=delete&id=${receta.id_receta}" onclick="return confirm('¿Está seguro?');">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>

