<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Receta</title>
</head>
<body>
    <h1>Editar Receta</h1>
    <form action="RecetaServlet?action=update" method="post">
        <input type="hidden" name="id" value="${receta.id_receta}">
        
        <label for="nombre_receta">Nombre de la Receta:</label>
        <input type="text" id="nombre_receta" name="nombre_receta" value="${receta.nombreReceta}" required><br>

        <label for="tipo_cocina">Tipo de Cocina:</label>
        <input type="text" id="tipo_cocina" name="tipo_cocina" value="${receta.tipoCocina}" required><br>

        <label for="ingredientes">Ingredientes:</label>
        <textarea id="ingredientes" name="ingredientes" required>${receta.ingredientes}</textarea><br>

        <label for="tiempo_preparacion">Tiempo de Preparación (minutos):</label>
        <input type="number" id="tiempo_preparacion" name="tiempo_preparacion" value="${receta.tiempoPreparacion}" min="1" required><br>

        <label for="estado">Estado:</label>
        <select id="estado" name="estado">
            <option value="1" ${receta.estado ? "selected" : ""}>Activa</option>
            <option value="0" ${!receta.estado ? "selected" : ""}>Inactiva</option>
        </select><br>

        <button type="submit">Actualizar</button>
        <a href="RecetaServlet?action=list">Cancelar</a>
    </form>
</body>
</html>
