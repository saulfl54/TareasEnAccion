package com.saulf.proyectodaw.web.app.models.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.saulf.proyectodaw.web.app.models.entity.Comentario;
import com.saulf.proyectodaw.web.app.models.entity.Tarea;
import com.saulf.proyectodaw.web.app.models.entity.Usuario;

public interface IUsuarioService {

	public List<Usuario> findAll();

	public void save(Usuario usuario);

	public Usuario findOne(Long id);

	public void delete(Long id);

	public Page<Usuario> findAll(Pageable pageable);

	public void saveTarea(Tarea tarea);

	public Tarea findTareaById(Long id);

	void deleteTarea(Long id);

	public List<Tarea> findAllOrderByCreateAtDes();

	public List<Tarea> findAllOrderByIdDesc();

	public void saveComentario(Comentario comentario, Usuario usuario, Tarea tarea);

	public Usuario findByUsername(String username);

	public Comentario findComentarioById(Long id);

	public void deleteComentario(Long id);

	public Boolean isAdmin(Long id);
}
