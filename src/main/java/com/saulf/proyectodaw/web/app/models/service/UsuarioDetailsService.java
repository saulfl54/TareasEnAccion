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
 * Servicio para cargar los datos del usuario para la autenticación con Spring Security.
 * Esta clase implementa la interfaz {@link UserDetailsService} y se encarga de cargar los datos
 * del usuario (como su nombre, contraseña y roles) desde la base de datos.
 * 
 * @author saulf
 */
@Service
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private IUsuarioDao usuarioDao;

    private Logger logger = LoggerFactory.getLogger(UsuarioDetailsService.class);

    /**
     * Carga los detalles de un usuario a partir de su nombre de usuario.
     * Este método consulta la base de datos para obtener el usuario y sus roles. Si el usuario
     * no existe o no tiene roles asignados, se lanzan excepciones correspondientes.
     * 
     * @param username El nombre de usuario del usuario a cargar.
     * @return Un objeto {@link UserDetails} que contiene los detalles del usuario.
     * @throws UsernameNotFoundException Si el usuario no existe o no tiene roles asignados.
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Buscar al usuario por su nombre de usuario
        Usuario usuario = usuarioDao.findByUsername(username);

        // Si no se encuentra al usuario, se lanza una excepción
        if (usuario == null) {
            logger.error("Error en el Login: el usuario '" + username + "' no existe!");
            throw new UsernameNotFoundException("El usuario '" + username + "' no existe!");
        }

        // Crear la lista de autoridades (roles) para Spring Security
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        // Asignar los roles del usuario a las autoridades
        for (Role role : usuario.getRoles()) {
            logger.info("Role: " + role.getRole());
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }

        // Verificar si el usuario tiene roles asignados
        if (authorities.isEmpty()) {
            logger.error("Error en el Login: Usuario '" + username + "' no tiene roles asignados!");
            throw new UsernameNotFoundException(
                    "Error en el Login: usuario '" + username + "' no tiene roles asignados!");
        }

        // Retornar el objeto UserDetails con los detalles del usuario y sus roles
        // UserDetails es la interfaz que representa un usuario autenticado en Spring Security
        return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true,
                authorities);
    }

}
