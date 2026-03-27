<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Leads - Sistema Gestión de Leads</title>
</head>
<body>
    <h1>Lista de Leads</h1>

    <!-- Botón para ir al formulario de creación -->
    <a href="leads?accion=nuevo">Nuevo Lead</a>

    <!-- Tabla que muestra todos los leads registrados -->
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Email</th>
                <th>Estado</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <!-- Iteración sobre la lista de leads enviada por el Servlet -->
            <c:forEach var="lead" items="${leads}">
                <tr>
                    <td>${lead.idLead}</td>
                    <td>${lead.nombre}</td>
                    <td>${lead.email}</td>
                    <td>${lead.estado}</td>
                    <td>
                        <!-- Acción editar: GET con parámetros -->
                        <a href="leads?accion=editar&id=${lead.idLead}">Editar</a>
                        <!-- Acción eliminar: POST con método oculto -->
                        <form method="post" action="leads" style="display:inline">
                            <input type="hidden" name="accion" value="eliminar"/>
                            <input type="hidden" name="id" value="${lead.idLead}"/>
                            <button type="submit">Eliminar</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>