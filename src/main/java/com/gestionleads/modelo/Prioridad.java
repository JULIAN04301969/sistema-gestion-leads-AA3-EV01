package com.gestionleads.modelo;

import jakarta.persistence.*;

/**
 * Entidad Prioridad - nivel de prioridad asignado a un lead.
 * Mapea la tabla 'prioridad' de la base de datos sistema_gestion_leads.
 * @author JULIAN
 * @version 1.0
 */
@Entity
@Table(name = "prioridad")
public class Prioridad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prioridad")
    private int idPrioridad;

    // Nombre único del nivel de prioridad
    @Column(name = "nombre_prioridad", nullable = false)
    private String nombrePrioridad;

    public Prioridad() {}

    public int getIdPrioridad() { return idPrioridad; }
    public void setIdPrioridad(int idPrioridad) { this.idPrioridad = idPrioridad; }

    public String getNombrePrioridad() { return nombrePrioridad; }
    public void setNombrePrioridad(String nombrePrioridad) { this.nombrePrioridad = nombrePrioridad; }
}