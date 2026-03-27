package com.gestionleads.modelo;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidad HistorialEstado - registro de cambios de estado de un lead.
 * Mapea la tabla 'historial_estado' de la base de datos sistema_gestion_leads.
 * @author JULIAN
 * @version 1.0
 */
@Entity
@Table(name = "historial_estado")
public class HistorialEstado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial")
    private int idHistorial;

    // Estado anterior del lead antes del cambio
    @Column(name = "estado_anterior", nullable = false)
    private String estadoAnterior;

    // Nuevo estado asignado al lead
    @Column(name = "estado_nuevo", nullable = false)
    private String estadoNuevo;

    // Fecha y hora en que se realizó el cambio
    @Column(name = "fecha_cambio", nullable = false)
    private LocalDateTime fechaCambio;

    // Lead al que corresponde el cambio de estado
    @ManyToOne
    @JoinColumn(name = "id_lead", nullable = false)
    private Lead lead;

    // Usuario que realizó el cambio de estado
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    public HistorialEstado() {}

    public int getIdHistorial() { return idHistorial; }
    public void setIdHistorial(int idHistorial) { this.idHistorial = idHistorial; }

    public String getEstadoAnterior() { return estadoAnterior; }
    public void setEstadoAnterior(String estadoAnterior) { this.estadoAnterior = estadoAnterior; }

    public String getEstadoNuevo() { return estadoNuevo; }
    public void setEstadoNuevo(String estadoNuevo) { this.estadoNuevo = estadoNuevo; }

    public LocalDateTime getFechaCambio() { return fechaCambio; }
    public void setFechaCambio(LocalDateTime fechaCambio) { this.fechaCambio = fechaCambio; }

    public Lead getLead() { return lead; }
    public void setLead(Lead lead) { this.lead = lead; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}