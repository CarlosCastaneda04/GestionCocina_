<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.models.Usuario" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");

    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Inicio</title>
</head>
<body>
    <h2>Bienvenido, <%= usuario.getNombre() %> <%= usuario.getApellido() %></h2>
    <p>Has iniciado sesión exitosamente.</p>
    <a href="LogoutServlet">Cerrar sesión</a>
</body>
</html>
