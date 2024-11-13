<!-- error.jsp -->
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
</head>
<body>
    <h2>Hubo un error al procesar tu solicitud.</h2>
    <p><%= request.getParameter("error") %></p>
    <a href="registroUsuario.jsp">Volver al registro de usuario</a>
</body>
</html>

