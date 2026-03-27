<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Seguimientos - Sistema Gestión de Leads</title>
</head>
<body>
    <h1>Lista de Seguimientos</h1>

    <a href="seguimientos?accion=nuevo">Nuevo Seguimiento</a>

    <!-- Tabla que muestra todos los seguimientos registrados -->
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Fecha</th>
                <th>Tipo Actividad</th>
                <th>Descripción</th>
                <th>Lead</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="seg" items="${seguimientos}">
                <tr>
                    <td>${seg.idSeguimiento}</td>
                    <td>${seg.fechaRegistro}</td>
                    <td>${seg.tipoActividad}</td>
                    <td>${seg.descripcion}</td>
                    <td>${seg.lead.nombre}</td>
                    <td>
                        <a href="seguimientos?accion=editar&id=${seg.idSeguimiento}">Editar</a>
                        <form method="post" action="seguimientos" style="display:inline">
                            <input type="hidden" name="accion" value="eliminar"/>
                            <input type="hidden" name="id" value="${seg.idSeguimiento}"/>
                            <button type="submit">Eliminar</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>