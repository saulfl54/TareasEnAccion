package com.saulf.proyectodaw.web.app.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.saulf.proyectodaw.web.app.models.entity.Comentario;

/**
 * Repositorio para la entidad Comentario. Extiende CrudRepository para proporcionar 
 * operaciones CRUD básicas sobre la entidad Comentario.
 * 
 * La interfaz permite realizar las operaciones de creación, lectura, actualización 
 * y eliminación (CRUD) sin necesidad de escribir implementaciones específicas.
 * 
 * @author saulf
 */
public interface IComentarioDao extends CrudRepository<Comentario, Long> {
    // No es necesario agregar métodos adicionales si solo se requieren las operaciones CRUD básicas.
}
