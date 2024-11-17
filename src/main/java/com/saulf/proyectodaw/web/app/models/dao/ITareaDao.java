package com.saulf.proyectodaw.web.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.saulf.proyectodaw.web.app.models.entity.Tarea;

public interface ITareaDao extends PagingAndSortingRepository<Tarea, Long>, CrudRepository<Tarea, Long>{

		@Query("SELECT p FROM Tarea p ORDER BY p.createAt desc")
		List<Tarea> findAllOrderByCreateAtDes();

	}


