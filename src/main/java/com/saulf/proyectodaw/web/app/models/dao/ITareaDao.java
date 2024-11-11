package com.saulf.proyectodaw.web.app.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.saulf.proyectodaw.web.app.models.entity.Tarea;

public interface ITareaDao extends PagingAndSortingRepository<Tarea, Long>, CrudRepository<Tarea, Long>{

}

