package com.gestionleads.dao;

import com.gestionleads.HibernateUtil;
import com.gestionleads.modelo.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

/**
 * DAO para la entidad Usuario.
 * Implementa las operaciones CRUD sobre la tabla 'usuario'.
 * @author JULIAN OCAMPO
 * @version 1.0
 */
public class UsuarioDAO {

    /**
     * CREATE - Registra un nuevo usuario en el sistema.
     * @param usuario objeto Usuario a insertar
     */
    public void crear(Usuario usuario) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(usuario);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error al crear usuario: " + e.getMessage());
        }
    }

    /**
     * READ - Obtiene un usuario por su ID.
     * @param id identificador del usuario
     * @return objeto Usuario o null si no existe
     */
    public Usuario obtenerPorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Usuario.class, id);
        } catch (Exception e) {
            System.err.println("Error al obtener usuario: " + e.getMessage());
            return null;
        }
    }

    /**
     * READ - Obtiene todos los usuarios registrados.
     * @return lista de objetos Usuario
     */
    public List<Usuario> obtenerTodos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Usuario", Usuario.class).list();
        } catch (Exception e) {
            System.err.println("Error al listar usuarios: " + e.getMessage());
            return null;
        }
    }

    /**
     * UPDATE - Actualiza los datos de un usuario existente.
     * @param usuario objeto Usuario con los datos actualizados
     */
    public void actualizar(Usuario usuario) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(usuario);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error al actualizar usuario: " + e.getMessage());
        }
    }

    /**
     * DELETE - Elimina un usuario por su ID.
     * @param id identificador del usuario a eliminar
     */
    public void eliminar(int id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Usuario usuario = session.get(Usuario.class, id);
            if (usuario != null) {
                session.remove(usuario);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error al eliminar usuario: " + e.getMessage());
        }
    }
}