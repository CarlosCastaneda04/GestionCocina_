<%-- 
    Document   : login
    Created on : 12 nov. 2024, 22:10:51
    Author     : carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <h2>Iniciar Sesión</h2>
    <form action="LoginServlet" method="post">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
        <br><br>
        <label for="clave">Clave:</label>
        <input type="password" id="clave" name="clave" required>
        <br><br>
        <button type="submit">Ingresar</button>
    </form>
</body>
</html>

