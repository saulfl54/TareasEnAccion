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
import com.saulf.proyectodaw.web.app.models.entity.Tarea;
import com.saulf.proyectodaw.web.app.models.entity.Usuario;



@Service
public class UsuarioServiceImpl implements IUsuarioService{

	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private ITareaDao tareaDao;
	
	@Autowired
	private IComentarioDao comentarioDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return (List<Usuario>) usuarioDao.findAll();
	}
	@Override
	@Transactional
	public void save(Usuario usuario) {
		usuarioDao.save(usuario);
		
	}
	@Override
	@Transactional(readOnly = true)
	public Usuario findOne(Long id) {
		// TODO Auto-generated method stub
		return usuarioDao.findById(id).orElse(null);
	}
	@Override
	@Transactional
	public void delete(Long id) {
		usuarioDao.deleteById(id);
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> findAll(Pageable pageable) {
		
		return usuarioDao.findAll(pageable);
	}
	@Override
	@Transactional
	public void saveTarea(Tarea tarea) {
		tareaDao.save(tarea);
	}
	@Override
	@Transactional(readOnly = true)
	public Tarea findTareaById(Long id) {
		return tareaDao.findById(id).orElse(null);
	}
	@Override
	@Transactional
	public void deleteTarea(Long id) {
		tareaDao.deleteById(id);
	}
	
	@Override
	@Transactional
	public void saveComentario(Comentario comentario) {
		comentarioDao.save(comentario);	
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Tarea> findAllTareas(Pageable pageable) {
		return tareaDao.findAll(pageable);
	}

}
