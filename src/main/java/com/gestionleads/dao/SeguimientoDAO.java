package com.gestionleads.dao;

import com.gestionleads.HibernateUtil;
import com.gestionleads.modelo.Seguimiento;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

/**
 * DAO para la entidad Seguimiento.
 * Implementa las operaciones CRUD sobre la tabla 'seguimiento'.
 * @author JULIAN OCAMPO
 * @version 1.0
 */
public class SeguimientoDAO {

    /**
     * CREATE - Registra una nueva actividad de seguimiento.
     * @param seguimiento objeto Seguimiento a insertar
     */
    public void crear(Seguimiento seguimiento) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(seguimiento);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error al crear seguimiento: " + e.getMessage());
        }
    }

    /**
     * READ - Obtiene un seguimiento por su ID.
     * @param id identificador del seguimiento
     * @return objeto Seguimiento o null si no existe
     */
    public Seguimiento obtenerPorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Seguimiento.class, id);
        } catch (Exception e) {
            System.err.println("Error al obtener seguimiento: " + e.getMessage());
            return null;
        }
    }

    /**
     * READ - Obtiene todos los seguimientos registrados.
     * @return lista de objetos Seguimiento
     */
    public List<Seguimiento> obtenerTodos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Seguimiento", Seguimiento.class).list();
        } catch (Exception e) {
            System.err.println("Error al listar seguimientos: " + e.getMessage());
            return null;
        }
    }

    /**
     * UPDATE - Actualiza los datos de un seguimiento existente.
     * @param seguimiento objeto Seguimiento con los datos actualizados
     */
    public void actualizar(Seguimiento seguimiento) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(seguimiento);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error al actualizar seguimiento: " + e.getMessage());
        }
    }

    /**
     * DELETE - Elimina un seguimiento por su ID.
     * @param id identificador del seguimiento a eliminar
     */
    public void eliminar(int id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Seguimiento seguimiento = session.get(Seguimiento.class, id);
            if (seguimiento != null) {
                session.remove(seguimiento);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error al eliminar seguimiento: " + e.getMessage());
        }
    }
}