package com.gestionleads.servlet;

import com.gestionleads.dao.LeadDAO;
import com.gestionleads.modelo.AsesorComercial;
import com.gestionleads.modelo.Fuente;
import com.gestionleads.modelo.Lead;
import com.gestionleads.modelo.Prioridad;
import com.gestionleads.modelo.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class LeadServlet extends HttpServlet {

    private LeadDAO leadDAO;

    @Override
    public void init() {
        leadDAO = new LeadDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if ("editar".equals(accion) || "nuevo".equals(accion)) {
            if ("editar".equals(accion)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Lead lead = leadDAO.obtenerPorId(id);
                request.setAttribute("lead", lead);
            }
            request.getRequestDispatcher("/WEB-INF/vistas/formLead.jsp")
                   .forward(request, response);
        } else {
            List<Lead> leads = leadDAO.obtenerTodos();
            request.setAttribute("leads", leads);
            request.getRequestDispatcher("/WEB-INF/vistas/listaLeads.jsp")
                   .forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");
        if ("eliminar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            leadDAO.eliminar(id);
        } else if ("actualizar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Lead lead = leadDAO.obtenerPorId(id);
            lead.setNombre(request.getParameter("nombre"));
            lead.setEmail(request.getParameter("email"));
            lead.setEstado(request.getParameter("estado"));
            leadDAO.actualizar(lead);
        } else {
            Lead lead = new Lead();
            lead.setNombre(request.getParameter("nombre"));
            lead.setEmail(request.getParameter("email"));
            lead.setEstado(request.getParameter("estado"));
            lead.setFechaRegistro(LocalDate.now());
            Fuente fuente = new Fuente();
            fuente.setIdFuente(1);
            lead.setFuente(fuente);
            Prioridad prioridad = new Prioridad();
            prioridad.setIdPrioridad(1);
            lead.setPrioridad(prioridad);
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(1);
            lead.setUsuarioCreador(usuario);
            leadDAO.crear(lead);
        }
        response.sendRedirect(request.getContextPath() + "/leads");
    }
}
