<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
</head>
<body>
    <h1>Ocurri� un Error</h1>
    <p>${param.error != null ? param.error : "Ocurri� un error inesperado."}</p>
    <a href="UsuarioServlet?action=list">Regresar a la lista de usuarios</a>
</body>
</html>


