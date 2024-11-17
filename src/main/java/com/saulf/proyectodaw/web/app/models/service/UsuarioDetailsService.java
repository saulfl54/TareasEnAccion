package com.saulf.proyectodaw.web.app.models.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.User;
import com.saulf.proyectodaw.web.app.models.dao.IUsuarioDao;
import com.saulf.proyectodaw.web.app.models.entity.Role;
import com.saulf.proyectodaw.web.app.models.entity.Usuario;



/**
 * Clase Service para cargar los datos del usuario
 * @author saulf
 *
 */

@Service
public class UsuarioDetailsService implements UserDetailsService{

	@Autowired
	private IUsuarioDao usuarioDao;
	
	private Logger logger = LoggerFactory.getLogger(UsuarioDetailsService.class);
	
	//con la misma transacción vamos a realizar la consulta del usuario y además vamos a obtener los roles
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
        Usuario usuario = usuarioDao.findByUsername(username);
        
        if(usuario == null) {
        	logger.error("Error en el Login: el usuario '" + username + "' no existe!");
        	throw new UsernameNotFoundException("El usuario '" + username + "' no existe!");
        }
        
       
        //creamos la lista de la implementación de SpringSecurity
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        //obtenemos los roles uno a uno y los guardamos en una lista 
        for(Role role: usuario.getRoles()) {
        	logger.info("Role: " + role.getRole());
        	authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        
        if(authorities.isEmpty()) {
        	logger.error("Error en el Login: Usuario '" + username + "' no tiene roles asignados!");
        	throw new UsernameNotFoundException("Error en el Login: usuario '" + username + "' no tiene roles asignados!");
        }
        
        //le pasamos los datos con los roles ya del tipo de la implementación de SpringSecurity.
        
        //UserDetails es una interfaz que representa un Usuario autenticado
        //new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities)
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
	}

}