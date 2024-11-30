package com.saulf.proyectodaw.web.app.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.saulf.proyectodaw.web.app.models.entity.Usuario;

/**
 * Repositorio para la entidad Usuario. Extiende PagingAndSortingRepository para 
 * operaciones de paginación y ordenación, y CrudRepository para las operaciones CRUD básicas.
 * 
 * @author saulf
 */
public interface IUsuarioDao extends PagingAndSortingRepository<Usuario, Long>, CrudRepository<Usuario, Long> {

    /**
     * Busca un usuario por su nombre de usuario.
     * 
     * @param username El nombre de usuario.
     * @return El usuario con el nombre de usuario especificado, o null si no existe.
     */
    Usuario findByUsername(String username);
}
