<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
</head>
<body>
    <h1>Ocurrió un Error</h1>
    <p>${param.error != null ? param.error : "Ocurrió un error inesperado."}</p>
    <a href="UsuarioServlet?action=list">Regresar a la lista de usuarios</a>
</body>
</html>


