package com.saulf.proyectodaw.web.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * Representa un comentario hecho por un usuario en el sistema. 
 * La clase está mapeada a la tabla "comentarios" en la base de datos y contiene
 * los detalles de un comentario, como su contenido y la fecha de creación.
 * 
 * Esta entidad está asociada a un usuario que crea el comentario.
 * 
 * @author saulf
 */
@Entity
@Table(name = "comentarios")
public class Comentario implements Serializable {

    /**
     * Identificador único del comentario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Contenido del comentario escrito por el usuario.
     */
    private String contenido;

    /**
     * Fecha en que el comentario fue creado.
     * Se persiste en formato de fecha (sin hora).
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private Date createAt;

    /**
     * Método que se ejecuta antes de persistir el comentario en la base de datos.
     * Establece la fecha de creación del comentario.
     */
    @PrePersist
    public void prePersist() {
        createAt = new Date();
    }

    /**
     * Relación Many-to-One con la entidad Usuario. Un comentario es creado por un único usuario.
     * La relación es de tipo Lazy, es decir, los datos del usuario no se cargan hasta que se necesiten.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    /**
     * Obtiene el identificador del comentario.
     * 
     * @return El identificador del comentario.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador del comentario.
     * 
     * @param id El identificador del comentario.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el contenido del comentario.
     * 
     * @return El contenido del comentario.
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * Establece el contenido del comentario.
     * 
     * @param contenido El contenido del comentario.
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    /**
     * Obtiene la fecha en que se creó el comentario.
     * 
     * @return La fecha de creación del comentario.
     */
    public Date getCreateAt() {
        return createAt;
    }

    /**
     * Establece la fecha de creación del comentario.
     * 
     * @param createAt La fecha de creación del comentario.
     */
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    /**
     * Obtiene el usuario que creó el comentario.
     * 
     * @return El usuario que creó el comentario.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario que creó el comentario.
     * 
     * @param usuario El usuario que creó el comentario.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    private static final long serialVersionUID = 1L;
}
