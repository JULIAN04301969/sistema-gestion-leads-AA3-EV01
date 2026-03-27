package com.gestionleads.servlet;

import com.gestionleads.dao.UsuarioDAO;
import com.gestionleads.modelo.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet para gestionar las operaciones CRUD del módulo Usuario.
 * Maneja peticiones GET para listar, POST para crear, actualizar y eliminar.
 * @author JULIAN OCAMPO
 * @version 1.0
 */
@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet {

    // DAO para acceso a datos de la entidad Usuario
    private UsuarioDAO usuarioDAO;

    @Override
    public void init() {
        usuarioDAO = new UsuarioDAO();
    }

    /**
     * GET - Lista todos los usuarios o muestra formulario de edición.
     * Parámetro opcional: id para obtener un usuario específico.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if ("editar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Usuario usuario = usuarioDAO.obtenerPorId(id);
            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher("/WEB-INF/vistas/formUsuario.jsp")
                   .forward(request, response);
        } else {
            List<Usuario> usuarios = usuarioDAO.obtenerTodos();
            request.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("/WEB-INF/vistas/listaUsuarios.jsp")
                   .forward(request, response);
        }
    }

    /**
     * POST - Procesa creación, actualización o eliminación de un usuario.
     * Parámetro accion: 'crear', 'actualizar' o 'eliminar'.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");

        if ("eliminar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            usuarioDAO.eliminar(id);

        } else if ("actualizar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Usuario usuario = usuarioDAO.obtenerPorId(id);
            usuario.setNombre(request.getParameter("nombre"));
            usuarioDAO.actualizar(usuario);

        } else {
            Usuario usuario = new Usuario();
            usuario.setNombre(request.getParameter("nombre"));
            usuarioDAO.crear(usuario);
        }

        response.sendRedirect(request.getContextPath() + "/usuarios");
    }
}