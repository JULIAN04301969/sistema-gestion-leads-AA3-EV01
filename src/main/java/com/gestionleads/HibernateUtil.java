package com.gestionleads;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Clase utilitaria para gestionar el SessionFactory de Hibernate.
 * Proporciona una única instancia compartida en toda la aplicación.
 * @author JULIAN
 * @version 1.0
 */
public class HibernateUtil {

    // Instancia única del SessionFactory (patrón Singleton)
    private static SessionFactory sessionFactory;

    // Bloque estático que inicializa el SessionFactory al cargar la clase
    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Error al inicializar Hibernate: " + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * Retorna la instancia única del SessionFactory.
     * @return SessionFactory configurado con hibernate.cfg.xml
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Cierra el SessionFactory al finalizar la aplicación.
     */
    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
