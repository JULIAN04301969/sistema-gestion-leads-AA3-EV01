package com.gestionleads.modelo;

import jakarta.persistence.*;

/**
 * Entidad Rol - define los roles de acceso del sistema.
 * Mapea la tabla 'rol' de la base de datos sistema_gestion_leads.
 * @author JULIAN
 * @version 1.0
 */
@Entity
@Table(name = "rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private int idRol;

    // Nombre único del rol en el sistema
    @Column(name = "nombre_rol", nullable = false)
    private String nombreRol;

    public Rol() {}

    public int getIdRol() { return idRol; }
    public void setIdRol(int idRol) { this.idRol = idRol; }

    public String getNombreRol() { return nombreRol; }
    public void setNombreRol(String nombreRol) { this.nombreRol = nombreRol; }
}