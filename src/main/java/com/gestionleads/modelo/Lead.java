package com.gestionleads.modelo;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Entidad Lead - representa un prospecto de cliente en el sistema.
 * Mapea la tabla 'lead' de la base de datos sistema_gestion_leads.
 * @author JULIAN
 * @version 1.0
 */
@Entity
@Table(name = "`lead`")
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lead")
    private int idLead;

    // Nombre completo del prospecto
    @Column(name = "nombre", nullable = false)
    private String nombre;

    // Correo electrónico único del prospecto
    @Column(name = "email", nullable = false)
    private String email;

    // Fecha en que se registró el lead
    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro;

    // Fecha del último contacto realizado
    @Column(name = "fecha_ultimo_contacto")
    private LocalDate fechaUltimoContacto;

    // Estado actual del lead en el proceso comercial
    @Column(name = "estado", nullable = false)
    private String estado;

    // Retroalimentación registrada del cliente
    @Column(name = "feedback_cliente")
    private String feedbackCliente;

    // Relación con fuente de origen del lead
    @ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "id_fuente", nullable = false)
    private Fuente fuente;

    // Relación con prioridad asignada
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_prioridad", nullable = false)
    private Prioridad prioridad;

    // Asesor comercial asignado al lead
   @ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "id_asesor_asignado")
    private AsesorComercial asesorAsignado;

    // Usuario que creó el registro
    @ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "id_usuario_creador", nullable = false)
    private Usuario usuarioCreador;

    // Constructor vacío requerido por Hibernate
    public Lead() {}

    // Getters y Setters
    public int getIdLead() { return idLead; }
    public void setIdLead(int idLead) { this.idLead = idLead; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDate getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDate fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public LocalDate getFechaUltimoContacto() { return fechaUltimoContacto; }
    public void setFechaUltimoContacto(LocalDate fechaUltimoContacto) { this.fechaUltimoContacto = fechaUltimoContacto; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getFeedbackCliente() { return feedbackCliente; }
    public void setFeedbackCliente(String feedbackCliente) { this.feedbackCliente = feedbackCliente; }

    public Fuente getFuente() { return fuente; }
    public void setFuente(Fuente fuente) { this.fuente = fuente; }

    public Prioridad getPrioridad() { return prioridad; }
    public void setPrioridad(Prioridad prioridad) { this.prioridad = prioridad; }

    public AsesorComercial getAsesorAsignado() { return asesorAsignado; }
    public void setAsesorAsignado(AsesorComercial asesorAsignado) { this.asesorAsignado = asesorAsignado; }

    public Usuario getUsuarioCreador() { return usuarioCreador; }
    public void setUsuarioCreador(Usuario usuarioCreador) { this.usuarioCreador = usuarioCreador; }
}