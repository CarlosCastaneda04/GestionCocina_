<!DOCTYPE html>
<html>
<head>
    <title>Agregar Usuario</title>
</head>
<body>
    <h1>Agregar Nuevo Usuario</h1>
    <form action="UsuarioServlet?action=add" method="post">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required><br>

        <label for="apellido">Apellido:</label>
        <input type="text" id="apellido" name="apellido" required><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br>

        <label for="clave">Contraseña:</label>
        <input type="password" id="clave" name="clave" required><br>

        <button type="submit">Guardar</button>
        <a href="UsuarioServlet?action=list">Cancelar</a>
    </form>
</body>
</html>
