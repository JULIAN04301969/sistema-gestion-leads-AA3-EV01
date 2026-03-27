package com.gestionleads.modelo;

import jakarta.persistence.*;

/**
 * Entidad Fuente - origen de captación del lead.
 * Mapea la tabla 'fuente' de la base de datos sistema_gestion_leads.
 * @author JULIAN
 * @version 1.0
 */
@Entity
@Table(name = "fuente")
public class Fuente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fuente")
    private int idFuente;

    // Nombre único de la fuente de captación
    @Column(name = "nombre_fuente", nullable = false)
    private String nombreFuente;

    public Fuente() {}

    public int getIdFuente() { return idFuente; }
    public void setIdFuente(int idFuente) { this.idFuente = idFuente; }

    public String getNombreFuente() { return nombreFuente; }
    public void setNombreFuente(String nombreFuente) { this.nombreFuente = nombreFuente; }
}
