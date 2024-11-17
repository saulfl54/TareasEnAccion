package com.saulf.proyectodaw.web.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.saulf.proyectodaw.web.app.models.entity.Role;

/**
 * interfaz con las operaciones CRUD génericas en el repository Role
 * @author saulf
 *
 */
public interface IRoleDao extends CrudRepository<Role, Long>{

	
}
