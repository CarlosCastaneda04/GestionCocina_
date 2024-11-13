<%-- 
    Document   : insertarUsuario
    Created on : 13 nov. 2024, 00:14:40
    Author     : carlo
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Insertar Usuario</title>
</head>
<body>
    <h2>Insertar Usuario</h2>
    <form action="guardarUsuario" method="post">
        Nombre: <input type="text" name="nombre" required><br>
        Apellido: <input type="text" name="apellido" required><br>
        Email: <input type="email" name="email" required><br>
        Fecha de Registro: <input type="date" name="fecha_registro" required><br>
        Clave: <input type="password" name="clave" required><br>
        Estado: <select name="estado">
            <option value="1">Activo</option>
            <option value="2">Incapacitado</option>
            <option value="3">Despedido</option>
            <option value="4">Renunció</option>
        </select><br>
         Foto: <input type="file" name="foto"><br>
        Acceso al Sistema: <input type="checkbox" name="accesoSistema" checked><br>
        <button type="submit">Guardar Usuario</button>
    </form>
</body>
</html>
