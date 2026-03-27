<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulario Lead - Sistema Gestión de Leads</title>
</head>
<body>
    <h1>Formulario de Lead</h1>

    <!-- Formulario POST para crear o actualizar un lead -->
    <form method="post" action="leads">

        <!-- Campo oculto para indicar la acción al Servlet -->
        <input type="hidden" name="accion"
               value="${lead != null ? 'actualizar' : 'crear'}"/>

        <!-- Campo oculto con el ID cuando se edita un lead existente -->
        <input type="hidden" name="id" value="${lead.idLead}"/>

        <label>Nombre:</label><br/>
        <input type="text" name="nombre"
               value="${lead.nombre}" required/><br/><br/>

        <label>Email:</label><br/>
        <input type="email" name="email"
               value="${lead.email}" required/><br/><br/>

        <label>Estado:</label><br/>
        <!-- Selector de estado del lead en el proceso comercial -->
        <select name="estado">
            <option value="Nuevo">Nuevo</option>
            <option value="En proceso">En proceso</option>
            <option value="Cerrado">Cerrado</option>
            <option value="Descartado">Descartado</option>
        </select><br/><br/>

        <button type="submit">Guardar</button>
        <a href="leads">Cancelar</a>
    </form>
</body>
</html>