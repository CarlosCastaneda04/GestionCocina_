<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- registroUsuario.jsp -->
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registro de Usuario</title>
</head>
<body>
    <h2>Registro de Usuario</h2>
    <form action="RegistroUsuarioServlet" method="POST" enctype="multipart/form-data">
        Nombre: <input type="text" name="nombre" required><br>
        Apellido: <input type="text" name="apellido" required><br>
        Email: <input type="email" name="email" required><br>
        Clave: <input type="password" name="clave" required><br>
        Estado: 
        <select name="estado" required>
            <option value="">Seleccione un estado</option>
            <option value="1">Activo</option>
            <option value="2">Incapacitado</option>
            <option value="3">Despedido</option>
            <option value="4">Renuncio</option>
        </select><br>
        Foto: <input type="file" name="foto" accept="image/*"><br>
        <input type="submit" value="Registrar">
    </form>
</body>
</html>
