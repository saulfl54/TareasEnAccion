package com.saulf.proyectodaw.web.app.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.saulf.proyectodaw.web.app.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {
}
