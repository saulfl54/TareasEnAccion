package com.saulf.proyectodaw.web.app.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.saulf.proyectodaw.web.app.models.entity.Comentario;

public interface IComentarioDao extends CrudRepository<Comentario, Long>{
}
