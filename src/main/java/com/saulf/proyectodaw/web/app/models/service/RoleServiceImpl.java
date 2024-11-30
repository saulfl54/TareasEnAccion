package com.saulf.proyectodaw.web.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saulf.proyectodaw.web.app.models.dao.IRoleDao;
import com.saulf.proyectodaw.web.app.models.entity.Role;

/**
 * Implementación del servicio para la gestión de roles.
 * Esta clase proporciona la lógica de negocio relacionada con la entidad {@link Role},
 * incluyendo métodos para recuperar todos los roles y un rol específico por su ID.
 * 
 * @author saulf
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    /**
     * Recupera todos los roles del sistema.
     * Este método devuelve una lista de todos los roles disponibles en la base de datos.
     * 
     * @return Una lista de objetos {@link Role} con todos los roles registrados.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Role> findAll() {
        return (List<Role>) roleDao.findAll();
    }

    /**
     * Recupera un rol específico por su ID.
     * Este método busca un rol en la base de datos usando su identificador único. 
     * Si el rol no existe, devuelve {@code null}.
     * 
     * @param id El identificador único del rol a recuperar.
     * @return El objeto {@link Role} correspondiente al ID proporcionado, o {@code null} si no se encuentra.
     */
    @Override
    @Transactional(readOnly = true)
    public Role findOne(Long id) {
        return roleDao.findById(id).orElse(null);
    }
}
