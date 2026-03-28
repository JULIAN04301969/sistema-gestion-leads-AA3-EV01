package com.gestionleads.servlet;

import com.gestionleads.dao.LeadDAO;
import com.gestionleads.modelo.Lead;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet para gestionar las operaciones CRUD del módulo Lead.
 * Maneja peticiones GET para listar y DELETE, POST para crear y actualizar.
 * @author JULIAN OCAMPO
 * @version 1.0
 */
public class LeadServlet extends HttpServlet {

    // DAO para acceso a datos de la entidad Lead
    private LeadDAO leadDAO;

    @Override
    public void init() {
        leadDAO = new LeadDAO();
    }

    /**
     * GET - Lista todos los leads o muestra el formulario de edición.
     * Parámetro opcional: id para obtener un lead específico.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if ("editar".equals(accion)) {
            // Obtener lead por ID para mostrar formulario de edición
            int id = Integer.parseInt(request.getParameter("id"));
            Lead lead = leadDAO.obtenerPorId(id);
            request.setAttribute("lead", lead);
            request.getRequestDispatcher("/WEB-INF/vistas/formLead.jsp")
                   .forward(request, response);
        } else {
            // Listar todos los leads
            List<Lead> leads = leadDAO.obtenerTodos();
            request.setAttribute("leads", leads);
            request.getRequestDispatcher("/WEB-INF/vistas/listaLeads.jsp")
                   .forward(request, response);
        }
    }

    /**
     * POST - Procesa creación o actualización de un lead.
     * Parámetro accion: 'crear' o 'actualizar' o 'eliminar'.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");

        if ("eliminar".equals(accion)) {
            // Eliminar lead por ID
            int id = Integer.parseInt(request.getParameter("id"));
            leadDAO.eliminar(id);

        } else if ("actualizar".equals(accion)) {
            // Actualizar lead existente
            int id = Integer.parseInt(request.getParameter("id"));
            Lead lead = leadDAO.obtenerPorId(id);
            lead.setNombre(request.getParameter("nombre"));
            lead.setEmail(request.getParameter("email"));
            lead.setEstado(request.getParameter("estado"));
            leadDAO.actualizar(lead);

        } else {
            // Crear nuevo lead
            Lead lead = new Lead();
            lead.setNombre(request.getParameter("nombre"));
            lead.setEmail(request.getParameter("email"));
            lead.setEstado(request.getParameter("estado"));
            leadDAO.crear(lead);
        }

        // Redirigir al listado después de la operación
        response.sendRedirect(request.getContextPath() + "/leads");
    }
}