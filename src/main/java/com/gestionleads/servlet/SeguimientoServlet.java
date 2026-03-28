package com.gestionleads.servlet;

import com.gestionleads.dao.SeguimientoDAO;
import com.gestionleads.modelo.Seguimiento;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet para gestionar las operaciones CRUD del módulo Seguimiento.
 * Maneja peticiones GET para listar, POST para crear, actualizar y eliminar.
 * @author JULIAN OCAMPO
 * @version 1.0
 */
public class SeguimientoServlet extends HttpServlet {

    // DAO para acceso a datos de la entidad Seguimiento
    private SeguimientoDAO seguimientoDAO;

    @Override
    public void init() {
        seguimientoDAO = new SeguimientoDAO();
    }

    /**
     * GET - Lista todos los seguimientos o muestra formulario de edición.
     * Parámetro opcional: id para obtener un seguimiento específico.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if ("editar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Seguimiento seguimiento = seguimientoDAO.obtenerPorId(id);
            request.setAttribute("seguimiento", seguimiento);
            request.getRequestDispatcher("/WEB-INF/vistas/formSeguimiento.jsp")
                   .forward(request, response);
        } else {
            List<Seguimiento> seguimientos = seguimientoDAO.obtenerTodos();
            request.setAttribute("seguimientos", seguimientos);
            request.getRequestDispatcher("/WEB-INF/vistas/listaSeguimientos.jsp")
                   .forward(request, response);
        }
    }

    /**
     * POST - Procesa creación, actualización o eliminación de un seguimiento.
     * Parámetro accion: 'crear', 'actualizar' o 'eliminar'.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");

        if ("eliminar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            seguimientoDAO.eliminar(id);

        } else if ("actualizar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Seguimiento seguimiento = seguimientoDAO.obtenerPorId(id);
            seguimiento.setTipoActividad(request.getParameter("tipoActividad"));
            seguimiento.setDescripcion(request.getParameter("descripcion"));
            seguimientoDAO.actualizar(seguimiento);

        } else {
            Seguimiento seguimiento = new Seguimiento();
            seguimiento.setTipoActividad(request.getParameter("tipoActividad"));
            seguimiento.setDescripcion(request.getParameter("descripcion"));
            seguimientoDAO.crear(seguimiento);
        }

        response.sendRedirect(request.getContextPath() + "/seguimientos");
    }
}