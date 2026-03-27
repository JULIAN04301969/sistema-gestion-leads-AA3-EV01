package com.gestionleads.modelo;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Entidad Descarte - registro de leads descartados del proceso comercial.
 * Mapea la tabla 'descarte' de la base de datos sistema_gestion_leads.
 * @author JULIAN
 * @version 1.0
 */
@Entity
@Table(name = "descarte")
public class Descarte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_descarte")
    private int idDescarte;

    // Motivo por el cual se descartó el lead
    @Column(name = "motivo", nullable = false)
    private String motivo;

    // Fecha en que se realizó el descarte
    @Column(name = "fecha_descarte", nullable = false)
    private LocalDate fechaDescarte;

    // Lead que fue descartado
    @ManyToOne
    @JoinColumn(name = "id_lead", nullable = false)
    private Lead lead;

    // Asesor que realizó el descarte
    @ManyToOne
    @JoinColumn(name = "id_asesor", nullable = false)
    private AsesorComercial asesor;

    public Descarte() {}

    public int getIdDescarte() { return idDescarte; }
    public void setIdDescarte(int idDescarte) { this.idDescarte = idDescarte; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public LocalDate getFechaDescarte() { return fechaDescarte; }
    public void setFechaDescarte(LocalDate fechaDescarte) { this.fechaDescarte = fechaDescarte; }

    public Lead getLead() { return lead; }
    public void setLead(Lead lead) { this.lead = lead; }

    public AsesorComercial getAsesor() { return asesor; }
    public void setAsesor(AsesorComercial asesor) { this.asesor = asesor; }
}