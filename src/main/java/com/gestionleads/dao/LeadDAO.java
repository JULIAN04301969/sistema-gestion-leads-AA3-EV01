package com.gestionleads.dao;

import com.gestionleads.HibernateUtil;
import com.gestionleads.modelo.Lead;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class LeadDAO {

    public void crear(Lead lead) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(lead);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            System.err.println("Error al crear lead: " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
    }

    public Lead obtenerPorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Lead.class, id);
        } catch (Exception e) {
            System.err.println("Error al obtener lead: " + e.getMessage());
            return null;
        }
    }

    public List<Lead> obtenerTodos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
    "SELECT l FROM Lead l " +
    "LEFT JOIN FETCH l.fuente " +
    "LEFT JOIN FETCH l.prioridad " +
    "LEFT JOIN FETCH l.usuarioCreador", Lead.class).list();
        } catch (Exception e) {
            System.err.println("Error al listar leads: " + e.getMessage());
            return null;
        }
    }

    public void actualizar(Lead lead) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.merge(lead);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            System.err.println("Error al actualizar lead: " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
    }

    public void eliminar(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Lead lead = session.get(Lead.class, id);
            if (lead != null) session.remove(lead);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            System.err.println("Error al eliminar lead: " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
    }
}