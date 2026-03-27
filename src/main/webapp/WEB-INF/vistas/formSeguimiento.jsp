<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulario Seguimiento - Sistema Gestión de Leads</title>
</head>
<body>
    <h1>Formulario de Seguimiento</h1>

    <!-- Formulario POST para crear o actualizar un seguimiento -->
    <form method="post" action="seguimientos">

        <input type="hidden" name="accion"
               value="${seguimiento != null ? 'actualizar' : 'crear'}"/>
        <input type="hidden" name="id" value="${seguimiento.idSeguimiento}"/>

        <label>Tipo de Actividad:</label><br/>
        <!-- Selector del tipo de actividad de seguimiento -->
        <select name="tipoActividad">
            <option value="Llamada">Llamada</option>
            <option value="Email">Email</option>
            <option value="Reunión">Reunión</option>
            <option value="Visita">Visita</option>
        </select><br/><br/>

        <label>Descripción:</label><br/>
        <textarea name="descripcion" rows="4" cols="40"
                  required>${seguimiento.descripcion}</textarea><br/><br/>

        <button type="submit">Guardar</button>
        <a href="seguimientos">Cancelar</a>
    </form>
</body>
</html>