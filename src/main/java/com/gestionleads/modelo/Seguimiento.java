package com.gestionleads.modelo;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidad Seguimiento - registro de actividades realizadas sobre un lead.
 * Mapea la tabla 'seguimiento' de la base de datos sistema_gestion_leads.
 * @author JULIAN
 * @version 1.0
 */
@Entity
@Table(name = "seguimiento")
public class Seguimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_seguimiento")
    private int idSeguimiento;

    // Fecha y hora del registro del seguimiento
    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;

    // Tipo de actividad realizada
    @Column(name = "tipo_actividad", nullable = false)
    private String tipoActividad;

    // Descripción detallada de la actividad
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    // Lead al que corresponde el seguimiento
    @ManyToOne
    @JoinColumn(name = "id_lead", nullable = false)
    private Lead lead;

    // Asesor que realizó el seguimiento
    @ManyToOne
    @JoinColumn(name = "id_asesor")
    private AsesorComercial asesor;

    public Seguimiento() {}

    public int getIdSeguimiento() { return idSeguimiento; }
    public void setIdSeguimiento(int idSeguimiento) { this.idSeguimiento = idSeguimiento; }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public String getTipoActividad() { return tipoActividad; }
    public void setTipoActividad(String tipoActividad) { this.tipoActividad = tipoActividad; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Lead getLead() { return lead; }
    public void setLead(Lead lead) { this.lead = lead; }

    public AsesorComercial getAsesor() { return asesor; }
    public void setAsesor(AsesorComercial asesor) { this.asesor = asesor; }
}