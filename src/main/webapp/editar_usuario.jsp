<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Usuario</title>
</head>
<body>
    <h1>Editar Usuario</h1>
    <form action="UsuarioServlet?action=update" method="post">
        <input type="hidden" name="id" value="${usuario.id_usuario}">
        
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" value="${usuario.nombre}" required><br>

        <label for="apellido">Apellido:</label>
        <input type="text" id="apellido" name="apellido" value="${usuario.apellido}" required><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="${usuario.email}" required><br>

        <button type="submit">Actualizar</button>
        <a href="UsuarioServlet?action=list">Cancelar</a>
    </form>
</body>
</html>
