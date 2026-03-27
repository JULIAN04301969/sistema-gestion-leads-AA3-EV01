package com.gestionleads.modelo;

import jakarta.persistence.*;

/**
 * Entidad Usuario - representa los usuarios del sistema.
 * Mapea la tabla 'usuario' de la base de datos sistema_gestion_leads.
 * @author JULIAN
 * @version 1.0
 */
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int idUsuario;

    // Nombre completo del usuario
    @Column(name = "nombre", nullable = false)
    private String nombre;

    // Rol asignado al usuario
    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;

    public Usuario() {}

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }
}