package com.saulf.proyectodaw.web.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.saulf.proyectodaw.web.app.models.entity.Tarea;

/**
 * Repositorio para la entidad Tarea. Extiende PagingAndSortingRepository para 
 * operaciones de paginación y ordenación, y CrudRepository para las operaciones CRUD básicas.
 * 
 * Además, se incluyen consultas personalizadas con la anotación @Query.
 * 
 * @author saulf
 */
public interface ITareaDao extends PagingAndSortingRepository<Tarea, Long>, CrudRepository<Tarea, Long> {

    /**
     * Obtiene todas las tareas ordenadas por su fecha de creación en orden descendente.
     * 
     * @return Una lista de tareas ordenadas por la fecha de creación (más recientes primero).
     */
    @Query("SELECT p FROM Tarea p ORDER BY p.createAt desc")
    List<Tarea> findAllOrderByCreateAtDes();

    /**
     * Obtiene todas las tareas ordenadas por su ID en orden descendente.
     * 
     * @return Una lista de tareas ordenadas por ID (más recientes primero).
     */
    @Query("SELECT p FROM Tarea p ORDER BY p.id DESC")
    List<Tarea> findAllOrderByIdDesc();
}
