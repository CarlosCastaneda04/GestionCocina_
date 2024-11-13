<!DOCTYPE html>
<html>
<head>
    <title>Agregar Receta</title>
</head>
<body>
    <h1>Agregar Nueva Receta</h1>
    <form action="RecetaServlet?action=add" method="post">
        <label for="nombre_receta">Nombre de la Receta:</label>
        <input type="text" id="nombre_receta" name="nombre_receta" required><br>

        <label for="tipo_cocina">Tipo de Cocina:</label>
        <input type="text" id="tipo_cocina" name="tipo_cocina" required><br>

        <label for="ingredientes">Ingredientes:</label>
        <textarea id="ingredientes" name="ingredientes" required></textarea><br>

        <label for="tiempo_preparacion">Tiempo de Preparación (minutos):</label>
        <input type="number" id="tiempo_preparacion" name="tiempo_preparacion" min="1" required><br>

        <label for="estado">Estado:</label>
        <select id="estado" name="estado">
            <option value="1">Activa</option>
            <option value="0">Inactiva</option>
        </select><br>

        <button type="submit">Guardar</button>
        <a href="RecetaServlet?action=list">Cancelar</a>
    </form>
</body>
</html>

