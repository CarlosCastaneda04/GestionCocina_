<%-- 
    Document   : login
    Created on : 12 nov. 2024, 22:10:51
    Author     : carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>Inicio de Sesión</h2>
<form action="/GestionCocina/LoginServlet" method="post">
    Email: <input type="email" name="email" required><br>
    Clave: <input type="password" name="clave" required><br>
    <button type="submit">Iniciar Sesión</button>
</form>
</body>
</html>

