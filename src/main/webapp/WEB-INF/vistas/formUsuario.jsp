<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulario Usuario - Sistema Gestión de Leads</title>
</head>
<body>
    <h1>Formulario de Usuario</h1>

    <!-- Formulario POST para crear o actualizar un usuario -->
    <form method="post" action="usuarios">

        <input type="hidden" name="accion"
               value="${usuario != null ? 'actualizar' : 'crear'}"/>
        <input type="hidden" name="id" value="${usuario.idUsuario}"/>

        <label>Nombre:</label><br/>
        <input type="text" name="nombre"
               value="${usuario.nombre}" required/><br/><br/>

        <button type="submit">Guardar</button>
        <a href="usuarios">Cancelar</a>
    </form>
</body>
</html>