package com.saulf.proyectodaw.web.app.models.entity;

import java.io.Serializable;
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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 * Clase que representa a un usuario en el sistema. La entidad se mapea a la tabla "usuarios" en 
 * la base de datos y contiene los datos del usuario, incluyendo sus tareas, comentarios y roles.
 * 
 * Cada usuario tiene un nombre, apellido, correo electrónico, un nombre de usuario único, 
 * una contraseña, una foto asociada y un conjunto de tareas y comentarios. Además, el usuario 
 * tiene roles asociados a él (por ejemplo, Administrador, Usuario, etc.).
 * 
 * @author saulf
 */
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

    /**
     * Constructor por defecto. Inicializa las listas de tareas, comentarios y roles.
     */
    public Usuario() {
        tareas = new ArrayList<Tarea>();
        comentarios = new ArrayList<Comentario>();
        roles = new ArrayList<Role>();
    }

    /**
     * Identificador único del usuario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre de usuario único, con restricción de longitud (4-20 caracteres).
     */
    @Column(length = 30, unique = true)
    @Size(min = 4, max = 20)
    private String username;

    /**
     * Contraseña del usuario. No puede estar vacía y tiene una longitud máxima de 60 caracteres.
     */
    @Column(length = 60)
    @NotEmpty
    private String password;

    /**
     * Estado del usuario (habilitado o deshabilitado).
     */
    private Boolean enabled;

    /**
     * Nombre del usuario. No puede estar vacío.
     */
    @NotEmpty
    private String nombre;

    /**
     * Apellido del usuario. No puede estar vacío.
     */
    @NotEmpty
    private String apellido;

    /**
     * Correo electrónico del usuario. Debe ser válido y no puede estar vacío.
     */
    @NotEmpty
    @Email
    private String email;

    /**
     * Foto del usuario (por ejemplo, URL de la imagen de perfil).
     */
    @Column(name = "foto")
    private String foto;

    /**
     * Lista de tareas asociadas al usuario. Relación con la entidad Tarea.
     */
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Tarea> tareas;

    /**
     * Lista de comentarios asociados al usuario. Relación con la entidad Comentario.
     */
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comentario> comentarios;

    /**
     * Lista de roles asociados al usuario. Relación con la entidad Role.
     */
    @Size(min = 1, max = 3)
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    /**
     * Fecha de creación del usuario.
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private Date createAt;

    /**
     * Método que se ejecuta antes de persistir el usuario para asignar la fecha de creación.
     */
    @PrePersist
    public void prePersist() {
        createAt = new Date();
    }

    /**
     * Obtiene el identificador del usuario.
     * 
     * @return El identificador único del usuario.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador del usuario.
     * 
     * @param id El identificador del usuario.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de usuario.
     * 
     * @return El nombre de usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario.
     * 
     * @param username El nombre de usuario.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtiene la contraseña del usuario.
     * 
     * @return La contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     * 
     * @param password La contraseña del usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene el estado del usuario (habilitado o deshabilitado).
     * 
     * @return El estado del usuario.
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * Establece el estado del usuario.
     * 
     * @param enabled El estado del usuario.
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Obtiene el nombre del usuario.
     * 
     * @return El nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     * 
     * @param nombre El nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellido del usuario.
     * 
     * @return El apellido del usuario.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Establece el apellido del usuario.
     * 
     * @param apellido El apellido del usuario.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     * 
     * @return El correo electrónico del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del usuario.
     * 
     * @param email El correo electrónico del usuario.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la fecha de creación del usuario.
     * 
     * @return La fecha de creación del usuario.
     */
    public Date getCreateAt() {
        return createAt;
    }

    /**
     * Establece la fecha de creación del usuario.
     * 
     * @param createAt La fecha de creación del usuario.
     */
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    /**
     * Obtiene la foto del usuario.
     * 
     * @return La foto del usuario.
     */
    public String getFoto() {
        return foto;
    }

    /**
     * Establece la foto del usuario.
     * 
     * @param foto La foto del usuario.
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     * Obtiene la lista de tareas asociadas al usuario.
     * 
     * @return La lista de tareas asociadas al usuario.
     */
    public List<Tarea> getTareas() {
        return tareas;
    }

    /**
     * Establece la lista de tareas asociadas al usuario.
     * 
     * @param tareas La lista de tareas asociadas al usuario.
     */
    public void setTareas(List<Tarea> tareas) {
        this.tareas = tareas;
    }

    /**
     * Añade una tarea a la lista de tareas del usuario.
     * 
     * @param tarea La tarea que se añade a la lista.
     */
    public void addTarea(Tarea tarea) {
        tareas.add(tarea);
    }

    /**
     * Obtiene la lista de comentarios asociados al usuario.
     * 
     * @return La lista de comentarios asociados al usuario.
     */
    public List<Comentario> getComentarios() {
        return comentarios;
    }

    /**
     * Establece la lista de comentarios asociados al usuario.
     * 
     * @param comentarios La lista de comentarios asociados al usuario.
     */
    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    /**
     * Añade un comentario a la lista de comentarios del usuario.
     * 
     * @param comentario El comentario que se añade a la lista.
     */
    public void addComentario(Comentario comentario) {
        comentarios.add(comentario);
    }

    /**
     * Obtiene la lista de roles asociados al usuario.
     * 
     * @return La lista de roles asociados al usuario.
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * Establece la lista de roles asociados al usuario.
     * 
     * @param roles La lista de roles asociados al usuario.
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    /**
     * Añade un rol a la lista de roles del usuario.
     * 
     * @param role El rol que se añade a la lista.
     */
    public void addRoles(Role role) {
        roles.add(role);
    }
    
    /**
     * Verifica si el usuario tiene el rol de Administrador.
     * 
     * @return true si el usuario tiene el rol de Administrador, false en caso contrario.
     */
    public boolean isAdmin() {
        return roles.stream().anyMatch(role -> "Administrador".equals(role.getNombre()));
    }

    private static final long serialVersionUID = 1L;
}
