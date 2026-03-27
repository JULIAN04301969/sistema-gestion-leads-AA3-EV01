<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Usuarios - Sistema Gestión de Leads</title>
</head>
<body>
    <h1>Lista de Usuarios</h1>

    <a href="usuarios?accion=nuevo">Nuevo Usuario</a>

    <!-- Tabla que muestra todos los usuarios del sistema -->
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Rol</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="usuario" items="${usuarios}">
                <tr>
                    <td>${usuario.idUsuario}</td>
                    <td>${usuario.nombre}</td>
                    <td>${usuario.rol.nombreRol}</td>
                    <td>
                        <a href="usuarios?accion=editar&id=${usuario.idUsuario}">Editar</a>
                        <form method="post" action="usuarios" style="display:inline">
                            <input type="hidden" name="accion" value="eliminar"/>
                            <input type="hidden" name="id" value="${usuario.idUsuario}"/>
                            <button type="submit">Eliminar</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>