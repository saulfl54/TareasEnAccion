package com.saulf.proyectodaw.web.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saulf.proyectodaw.web.app.models.dao.IRoleDao;
import com.saulf.proyectodaw.web.app.models.entity.Role;

/**
 * Clase servicio para los Roles
 * 
 * @author saulf
 *
 */

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private IRoleDao roleDao;

	/**
	 * devuelve la lista de roles
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Role> findAll() {
		return (List<Role>) roleDao.findAll();

	}

	/**
	 * devuelve un rol
	 */
	@Override
	@Transactional(readOnly = true)
	public Role findOne(Long id) {
		return roleDao.findById(id).orElse(null);
	}
}
