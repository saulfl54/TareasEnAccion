package com.saulf.proyectodaw.web.app.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.saulf.proyectodaw.web.app.models.entity.Role;

/**
 * Interfaz para las operaciones CRUD genéricas sobre el repositorio de Role.
 * Extiende CrudRepository para proveer métodos básicos para la gestión de los roles.
 * 
 * @author saulf
 */
public interface IRoleDao extends CrudRepository<Role, Long> {
    // No es necesario agregar métodos adicionales si solo se requieren las operaciones CRUD básicas.
}
