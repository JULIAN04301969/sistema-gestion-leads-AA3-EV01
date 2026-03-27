package com.gestionleads.modelo;

import jakarta.persistence.*;

/**
 * Entidad AsesorComercial - asesor encargado de gestionar leads.
 * Mapea la tabla 'asesor_comercial' de la base de datos sistema_gestion_leads.
 * @author JULIAN
 * @version 1.0
 */
@Entity
@Table(name = "asesor_comercial")
public class AsesorComercial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asesor")
    private int idAsesor;

    // Disponibilidad actual del asesor (1=disponible, 0=no disponible)
    @Column(name = "disponibilidad", nullable = false)
    private boolean disponibilidad;

    // Usuario asociado al asesor comercial
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    public AsesorComercial() {}

    public int getIdAsesor() { return idAsesor; }
    public void setIdAsesor(int idAsesor) { this.idAsesor = idAsesor; }

    public boolean isDisponibilidad() { return disponibilidad; }
    public void setDisponibilidad(boolean disponibilidad) { this.disponibilidad = disponibilidad; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}