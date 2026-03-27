package com.gestionleads.dao;

import com.gestionleads.HibernateUtil;
import com.gestionleads.modelo.Lead;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

/**
 * DAO para la entidad Lead.
 * Implementa las operaciones CRUD sobre la tabla 'lead'.
 * @author JULIAN OCAMPO
 * @version 1.0
 */
public class LeadDAO {

    /**
     * CREATE - Inserta un nuevo lead en la base de datos.
     * @param lead objeto Lead a insertar
     */
    public void crear(Lead lead) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(lead);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error al crear lead: " + e.getMessage());
        }
    }

    /**
     * READ - Obtiene un lead por su ID.
     * @param id identificador del lead
     * @return objeto Lead o null si no existe
     */
    public Lead obtenerPorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Lead.class, id);
        } catch (Exception e) {
            System.err.println("Error al obtener lead: " + e.getMessage());
            return null;
        }
    }

    /**
     * READ - Obtiene todos los leads registrados en el sistema.
     * @return lista de objetos Lead
     */
    public List<Lead> obtenerTodos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Lead", Lead.class).list();
        } catch (Exception e) {
            System.err.println("Error al listar leads: " + e.getMessage());
            return null;
        }
    }

    /**
     * UPDATE - Actualiza los datos de un lead existente.
     * @param lead objeto Lead con los datos actualizados
     */
    public void actualizar(Lead lead) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(lead);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error al actualizar lead: " + e.getMessage());
        }
    }

    /**
     * DELETE - Elimina un lead de la base de datos por su ID.
     * @param id identificador del lead a eliminar
     */
    public void eliminar(int id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Lead lead = session.get(Lead.class, id);
            if (lead != null) {
                session.remove(lead);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error al eliminar lead: " + e.getMessage());
        }
    }
}