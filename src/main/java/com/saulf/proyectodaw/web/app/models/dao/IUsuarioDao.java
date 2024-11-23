package com.saulf.proyectodaw.web.app.models.dao;

import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.saulf.proyectodaw.web.app.models.entity.Usuario;

public interface IUsuarioDao extends PagingAndSortingRepository<Usuario, Long>, CrudRepository<Usuario, Long> {

	Usuario findByUsername(String username);

}
