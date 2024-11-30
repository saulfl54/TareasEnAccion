package com.saulf.proyectodaw.web.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.saulf.proyectodaw.web.app.models.entity.Comentario;
import com.saulf.proyectodaw.web.app.models.entity.Tarea;
import com.saulf.proyectodaw.web.app.models.entity.Usuario;

/**
 * Interfaz para el servicio de gestión de usuarios, tareas y comentarios.
 * Esta interfaz define los métodos necesarios para la interacción
 * con las entidades {@link Usuario}, {@link Tarea} y {@link Comentario},
 * permitiendo realizar operaciones CRUD y otras operaciones adicionales.
 * 
 * @author saulf
 */
public interface IUsuarioService {

    /**
     * Recupera todos los usuarios en el sistema.
     * 
     * @return Una lista de objetos {@link Usuario}.
     */
    public List<Usuario> findAll();

    /**
     * Guarda o actualiza un usuario en el sistema.
     * 
     * @param usuario El objeto {@link Usuario} a guardar o actualizar.
     */
    public void save(Usuario usuario);

    /**
     * Recupera un usuario específico por su ID.
     * 
     * @param id El identificador único del usuario.
     * @return El objeto {@link Usuario} correspondiente al ID proporcionado.
     */
    public Usuario findOne(Long id);

    /**
     * Elimina un usuario por su ID.
     * 
     * @param id El identificador único del usuario a eliminar.
     */
    public void delete(Long id);

    /**
     * Recupera una página de usuarios, útil para paginación.
     * 
     * @param pageable El objeto {@link Pageable} que define las condiciones de paginación.
     * @return Una página de objetos {@link Usuario}.
     */
    public Page<Usuario> findAll(Pageable pageable);

    /**
     * Guarda o actualiza una tarea en el sistema.
     * 
     * @param tarea El objeto {@link Tarea} a guardar o actualizar.
     */
    public void saveTarea(Tarea tarea);

    /**
     * Recupera una tarea específica por su ID.
     * 
     * @param id El identificador único de la tarea.
     * @return El objeto {@link Tarea} correspondiente al ID proporcionado.
     */
    public Tarea findTareaById(Long id);

    /**
     * Elimina una tarea por su ID.
     * 
     * @param id El identificador único de la tarea a eliminar.
     */
    void deleteTarea(Long id);

    /**
     * Recupera todas las tareas ordenadas por la fecha de creación, de manera descendente.
     * 
     * @return Una lista de objetos {@link Tarea} ordenados por la fecha de creación.
     */
    public List<Tarea> findAllOrderByCreateAtDes();

    /**
     * Recupera todas las tareas ordenadas por su ID de manera descendente.
     * 
     * @return Una lista de objetos {@link Tarea} ordenados por ID.
     */
    public List<Tarea> findAllOrderByIdDesc();

    /**
     * Guarda un comentario y lo asocia a un usuario y una tarea.
     * 
     * @param comentario El objeto {@link Comentario} a guardar.
     * @param usuario El objeto {@link Usuario} que hace el comentario.
     * @param tarea El objeto {@link Tarea} a la que se asocia el comentario.
     */
    public void saveComentario(Comentario comentario, Usuario usuario, Tarea tarea);

    /**
     * Recupera un usuario específico por su nombre de usuario.
     * 
     * @param username El nombre de usuario a buscar.
     * @return El objeto {@link Usuario} correspondiente al nombre de usuario proporcionado.
     */
    public Usuario findByUsername(String username);

    /**
     * Recupera un comentario específico por su ID.
     * 
     * @param id El identificador único del comentario.
     * @return El objeto {@link Comentario} correspondiente al ID proporcionado.
     */
    public Comentario findComentarioById(Long id);

    /**
     * Elimina un comentario por su ID.
     * 
     * @param id El identificador único del comentario a eliminar.
     */
    public void deleteComentario(Long id);

    /**
     * Verifica si un usuario tiene el rol de administrador.
     * 
     * @param id El identificador único del usuario.
     * @return true si el usuario es administrador, false en caso contrario.
     */
    public Boolean isAdmin(Long id);
}
