<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestión de Usuarios</title>
</head>
<body>
    <h1>Lista de Usuarios</h1>
    <a href="nuevo_usuario.jsp">Agregar Nuevo Usuario</a>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Email</th>
            <th>Acciones</th>
        </tr>
        <c:forEach var="usuario" items="${usuarios}">
            <tr>
                <td>${usuario.id_usuario}</td>
                <td>${usuario.nombre}</td>
                <td>${usuario.apellido}</td>
                <td>${usuario.email}</td>
                <td>
                    <a href="UsuarioServlet?action=edit&id=${usuario.id_usuario}">Editar</a>
                    <a href="UsuarioServlet?action=delete&id=${usuario.id_usuario}" onclick="return confirm('¿Está seguro?');">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
