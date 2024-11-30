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
	 * Obtiene todos los usuarios registrados.
	 * 
	 * @return Lista de usuarios.
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioDao.findAll();
	}

	/**
	 * Guarda un nuevo usuario o actualiza un usuario existente.
	 * 
	 * @param usuario El usuario a guardar o actualizar.
	 */
	@Override
	@Transactional
	public void save(Usuario usuario) {
		usuarioDao.save(usuario);
	}

	/**
	 * Obtiene un usuario por su ID.
	 * 
	 * @param id El ID del usuario a buscar.
	 * @return El usuario encontrado o null si no existe.
	 */
	@Override
	@Transactional(readOnly = true)
	public Usuario findOne(Long id) {
		return usuarioDao.findById(id).orElse(null);
	}

	/**
	 * Elimina un usuario por su ID.
	 * 
	 * @param id El ID del usuario a eliminar.
	 */
	@Override
	@Transactional
	public void delete(Long id) {
		usuarioDao.deleteById(id);
	}

	/**
	 * Obtiene una lista paginada de usuarios.
	 * 
	 * @param pageable El objeto Pageable que define la página y el tamaño de la lista.
	 * @return Lista paginada de usuarios.
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> findAll(Pageable pageable) {
		return usuarioDao.findAll(pageable);
	}

	/**
	 * Guarda una tarea.
	 * 
	 * @param tarea La tarea a guardar.
	 */
	@Override
	@Transactional
	public void saveTarea(Tarea tarea) {
		tareaDao.save(tarea);
	}

	/**
	 * Obtiene una tarea por su ID.
	 * 
	 * @param id El ID de la tarea a buscar.
	 * @return La tarea encontrada o null si no existe.
	 */
	@Override
	@Transactional(readOnly = true)
	public Tarea findTareaById(Long id) {
		return tareaDao.findById(id).orElse(null);
	}

	/**
	 * Elimina una tarea por su ID.
	 * 
	 * @param id El ID de la tarea a eliminar.
	 */
	@Override
	@Transactional
	public void deleteTarea(Long id) {
		tareaDao.deleteById(id);
	}

	/**
	 * Obtiene todas las tareas ordenadas por fecha de creación de forma descendente.
	 * 
	 * @return Lista de tareas ordenadas por fecha de creación de forma descendente.
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Tarea> findAllOrderByCreateAtDes() {
		return tareaDao.findAllOrderByCreateAtDes();
	}

	/**
	 * Obtiene todas las tareas ordenadas por ID de forma descendente.
	 * 
	 * @return Lista de tareas ordenadas por ID de forma descendente.
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Tarea> findAllOrderByIdDesc() {
		return tareaDao.findAllOrderByIdDesc();
	}

	/**
	 * Obtiene un usuario por su nombre de usuario.
	 * 
	 * @param username El nombre de usuario a buscar.
	 * @return El usuario encontrado o null si no existe.
	 */
	@Override
	@Transactional(readOnly = true)
	public Usuario findByUsername(String username) {
		return usuarioDao.findByUsername(username);
	}

	/**
	 * Verifica si un usuario tiene el rol de administrador.
	 * 
	 * @param id El ID del usuario a verificar.
	 * @return True si el usuario tiene el rol de administrador, de lo contrario, false.
	 */
	@Override
	@Transactional(readOnly = true)
	public Boolean isAdmin(Long id) {
		Usuario usuario = findOne(id);

		// Verifica los roles del usuario
		for (Role role : usuario.getRoles()) {
			if (role.getRole().equals("ROLE_ADMIN"))
				return true;
		}
		return false;
	}

	/**
	 * Obtiene un comentario por su ID.
	 * 
	 * @param id El ID del comentario a buscar.
	 * @return El comentario encontrado o null si no existe.
	 */
	@Override
	@Transactional(readOnly = true)
	public Comentario findComentarioById(Long id) {
		return comentarioDao.findById(id).orElse(null);
	}

	/**
	 * Guarda un comentario asociado a un usuario y una tarea.
	 * 
	 * @param comentario El comentario a guardar.
	 * @param usuario El usuario que realiza el comentario.
	 * @param tarea La tarea asociada al comentario.
	 */
	@Override
	@Transactional
	public void saveComentario(Comentario comentario, Usuario usuario, Tarea tarea) {
		comentario.setUsuario(usuario);
		tarea.addComentario(comentario);
		comentarioDao.save(comentario);
	}

	/**
	 * Elimina un comentario por su ID.
	 * 
	 * @param id El ID del comentario a eliminar.
	 */
	@Override
	@Transactional
	public void deleteComentario(Long id) {
		comentarioDao.deleteById(id);
	}

}
