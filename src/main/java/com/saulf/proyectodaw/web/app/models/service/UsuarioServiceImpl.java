package com.saulf.proyectodaw.web.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saulf.proyectodaw.web.app.models.dao.IComentarioDao;
import com.saulf.proyectodaw.web.app.models.dao.ITareaDao;
import com.saulf.proyectodaw.web.app.models.dao.IUsuarioDao;
import com.saulf.proyectodaw.web.app.models.entity.Comentario;
import com.saulf.proyectodaw.web.app.models.entity.Role;
import com.saulf.proyectodaw.web.app.models.entity.Tarea;
import com.saulf.proyectodaw.web.app.models.entity.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioDao usuarioDao;

	@Autowired
	private ITareaDao tareaDao;

	@Autowired
	private IComentarioDao comentarioDao;

	/**
	 * devuelve la lista de usuarios
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioDao.findAll();
	}

	/**
	 * guarda un usuario
	 */
	@Override
	@Transactional
	public void save(Usuario usuario) {
		usuarioDao.save(usuario);

	}

	/**
	 * devuelve un usuario
	 */

	@Override
	@Transactional(readOnly = true)
	public Usuario findOne(Long id) {
		return usuarioDao.findById(id).orElse(null);
	}

	/**
	 * borra un usuario
	 */

	@Override
	@Transactional
	public void delete(Long id) {
		usuarioDao.deleteById(id);

	}

	/**
	 * devuelve una lista paginada de usuarios
	 */

	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> findAll(Pageable pageable) {

		return usuarioDao.findAll(pageable);
	}

	/**
	 * guarda una tarea
	 */
	@Override
	@Transactional
	public void saveTarea(Tarea tarea) {
		tareaDao.save(tarea);
	}

	/**
	 * devuelve una tarea
	 */
	@Override
	@Transactional(readOnly = true)
	public Tarea findTareaById(Long id) {
		return tareaDao.findById(id).orElse(null);
	}

	/**
	 * borra una tarea
	 */

	@Override
	@Transactional
	public void deleteTarea(Long id) {
		tareaDao.deleteById(id);
	}

	/**
	 * devuelve una lista de tareas ordenada por fecha descendente
	 */

	@Override
	@Transactional(readOnly = true)
	public List<Tarea> findAllOrderByCreateAtDes() {
		return tareaDao.findAllOrderByCreateAtDes();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tarea> findAllOrderByIdDesc() {
		return tareaDao.findAllOrderByIdDesc();
	}

	/**
	 * devuelve un usuario por su username
	 */

	@Override
	@Transactional(readOnly = true)
	public Usuario findByUsername(String username) {
		return usuarioDao.findByUsername(username);
	}

	/**
	 * si es admin devuelve true
	 */

	@Override
	@Transactional(readOnly = true)
	public Boolean isAdmin(Long id) {
		Usuario usuario = findOne(id);

		for (Role role : usuario.getRoles()) {

			if (role.getRole().equals("ROLE_ADMIN"))
				return true;
		}
		return false;
	}

	/**
	 * devuelve un comentario
	 */
	@Override
	@Transactional(readOnly = true)
	public Comentario findComentarioById(Long id) {
		return comentarioDao.findById(id).orElse(null);
	}

	/**
	 * guarda un comentario
	 */
	@Override
	@Transactional
	public void saveComentario(Comentario comentario, Usuario usuario, Tarea tarea) {

		comentario.setUsuario(usuario);
		tarea.addComentario(comentario);
		comentarioDao.save(comentario);
	}

	/**
	 * borra un comentario
	 */

	@Override
	@Transactional
	public void deleteComentario(Long id) {
		comentarioDao.deleteById(id);
	}

}
