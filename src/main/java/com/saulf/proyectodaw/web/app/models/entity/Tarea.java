package com.saulf.proyectodaw.web.app.models.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 * Clase que representa una tarea dentro del sistema. Esta entidad se mapea a la tabla "tareas" 
 * en la base de datos y contiene la información relacionada con una tarea, como su título, 
 * contenido, la imagen asociada, los comentarios relacionados y el usuario que creó la tarea.
 * 
 * Cada tarea puede tener múltiples comentarios y está asociada a un único usuario.
 * 
 * @author saulf
 */
@Entity
@Table(name = "tareas")
public class Tarea {

    /**
     * Constructor por defecto. Inicializa la lista de comentarios.
     */
    public Tarea() {
        comentarios = new ArrayList<Comentario>();
    }

    /**
     * Identificador único de la tarea.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Título de la tarea. No puede estar vacío y debe tener entre 7 y 40 caracteres.
     */
    @NotEmpty
    @Size(min = 7, max = 40, message = "El título no puede tener más de 40 caracteres ni menos de 7")
    private String titulo;

    /**
     * Contenido de la tarea. No puede estar vacío y debe tener entre 30 y 300 caracteres.
     */
    @NotEmpty
    @Size(min = 30, max = 300)
    private String contenido;

    /**
     * Imagen asociada a la tarea.
     */
    @Column(name = "imagen")
    private String imagen;

    /**
     * Usuario que creó la tarea. Relación con la entidad Usuario.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    /**
     * Lista de comentarios asociados a la tarea. Relación con la entidad Comentario.
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tarea_id") // Relación con Comentario
    private List<Comentario> comentarios;

    /**
     * Fecha en que se creó la tarea.
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private Date createAt;

    /**
     * Método que se ejecuta antes de persistir la tarea para asignar la fecha de creación.
     */
    @PrePersist
    public void prePersist() {
        createAt = new Date();
    }

    /**
     * Obtiene el identificador de la tarea.
     * 
     * @return El identificador único de la tarea.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador de la tarea.
     * 
     * @param id El identificador de la tarea.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el título de la tarea.
     * 
     * @return El título de la tarea.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece el título de la tarea.
     * 
     * @param titulo El título de la tarea.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene el contenido de la tarea.
     * 
     * @return El contenido de la tarea.
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * Establece el contenido de la tarea.
     * 
     * @param contenido El contenido de la tarea.
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    /**
     * Obtiene la imagen asociada a la tarea.
     * 
     * @return La imagen asociada a la tarea.
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Establece la imagen asociada a la tarea.
     * 
     * @param imagen La imagen asociada a la tarea.
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * Obtiene la lista de comentarios asociados a la tarea.
     * 
     * @return La lista de comentarios asociados a la tarea.
     */
    public List<Comentario> getComentarios() {
        return comentarios;
    }

    /**
     * Establece los comentarios asociados a la tarea.
     * 
     * @param comentarios La lista de comentarios asociados a la tarea.
     */
    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    /**
     * Añade un comentario a la lista de comentarios de la tarea.
     * 
     * @param comentario El comentario a añadir a la tarea.
     */
    public void addComentario(Comentario comentario) {
        this.comentarios.add(comentario);
    }

    /**
     * Obtiene la fecha de creación de la tarea.
     * 
     * @return La fecha de creación de la tarea.
     */
    public Date getCreateAt() {
        return createAt;
    }

    /**
     * Establece la fecha de creación de la tarea.
     * 
     * @param createAt La fecha de creación de la tarea.
     */
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    /**
     * Obtiene el usuario que creó la tarea.
     * 
     * @return El usuario que creó la tarea.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario que creó la tarea.
     * 
     * @param usuario El usuario que creó la tarea.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    private static final long serialVersionUID = 1L;
}
