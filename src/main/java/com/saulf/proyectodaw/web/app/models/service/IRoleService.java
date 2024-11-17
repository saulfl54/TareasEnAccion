package com.saulf.proyectodaw.web.app.models.service;

import java.util.List;

import com.saulf.proyectodaw.web.app.models.entity.Role;

/**
 * Interfaz service para los Roles
 * @author saulf
 *
 */

public interface IRoleService {
	
	public List<Role> findAll();
	public Role findOne(Long id);
	

}

