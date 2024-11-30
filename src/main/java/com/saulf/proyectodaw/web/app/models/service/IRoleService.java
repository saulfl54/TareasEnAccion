package com.saulf.proyectodaw.web.app.models.service;

import java.util.List;

import com.saulf.proyectodaw.web.app.models.entity.Role;

/**
 * Interfaz para el servicio de gestión de roles.
 * Esta interfaz define los métodos necesarios para la interacción
 * con la entidad {@link Role} en el contexto de la lógica de negocio.
 * Permite la obtención de roles tanto de manera individual como en lista.
 * 
 * @author saulf
 */
public interface IRoleService {

    /**
     * Recupera todos los roles disponibles.
     * 
     * @return Una lista de objetos {@link Role}, que representan los roles en el sistema.
     */
    public List<Role> findAll();

    /**
     * Recupera un rol específico mediante su ID.
     * 
     * @param id El identificador único del rol que se desea obtener.
     * @return Un objeto {@link Role} correspondiente al ID proporcionado,
     *         o null si no se encuentra el rol.
     */
    public Role findOne(Long id);
}
