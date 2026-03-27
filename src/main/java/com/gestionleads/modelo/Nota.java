package com.gestionleads.modelo;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidad Nota - observaciones registradas sobre un lead.
 * Mapea la tabla 'nota' de la base de datos sistema_gestion_leads.
 * @author JULIAN
 * @version 1.0
 */
@Entity
@Table(name = "nota")
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nota")
    private int idNota;

    // Fecha y hora de creación de la nota
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    // Contenido textual de la nota
    @Column(name = "contenido", nullable = false)
    private String contenido;

    // Lead al que pertenece la nota
    @ManyToOne
    @JoinColumn(name = "id_lead", nullable = false)
    private Lead lead;

    // Usuario que creó la nota
    @ManyToOne
    @JoinColumn(name = "id_usuario_creador")
    private Usuario usuarioCreador;

    public Nota() {}

    public int getIdNota() { return idNota; }
    public void setIdNota(int idNota) { this.idNota = idNota; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }

    public Lead getLead() { return lead; }
    public void setLead(Lead lead) { this.lead = lead; }

    public Usuario getUsuarioCreador() { return usuarioCreador; }
    public void setUsuarioCreador(Usuario usuarioCreador) { this.usuarioCreador = usuarioCreador; }
}