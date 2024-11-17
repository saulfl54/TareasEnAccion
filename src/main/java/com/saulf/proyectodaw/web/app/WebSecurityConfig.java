package com.saulf.proyectodaw.web.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.saulf.proyectodaw.web.app.auth.ExitoLogin;
import com.saulf.proyectodaw.web.app.models.service.UsuarioDetailsService;

/**
 * Clase de configuración de Spring Security
 * 
 * @author saulf
 *
 */
@Configuration
public class WebSecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioDetailsService usuarioDetailsService;
    private final ExitoLogin exitoLogin;

    public WebSecurityConfig(PasswordEncoder passwordEncoder, UsuarioDetailsService usuarioDetailsService, ExitoLogin exitoLogin) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioDetailsService = usuarioDetailsService;
        this.exitoLogin = exitoLogin;
    }

    /**
     * Configura la seguridad HTTP de la aplicación, incluyendo autorización, login,
     * logout, y manejo de excepciones.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/css/**", "/js/**", "/images/**").permitAll()
                .requestMatchers("/usuario/listar").hasRole("USER")
                .requestMatchers("/tarea/listar").hasRole("USER")
                .requestMatchers("/usuario/ver/**").hasRole("USER")
                .requestMatchers("/tarea/ver/**").hasRole("USER")
                .requestMatchers("/uploads/**").hasRole("USER")
                .requestMatchers("/tarea/form/**").hasRole("ADVANCED_USER")
                .requestMatchers("/tarea/eliminar/**").hasRole("ADVANCED_USER")
                .requestMatchers("/usuario/eliminar/**").hasRole("ADMIN")
                .requestMatchers("/usuario/form/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .successHandler(exitoLogin)
                .loginPage("/login")
                .permitAll()
            )
            .logout(logout -> logout.permitAll())
            .exceptionHandling(exception -> exception.accessDeniedPage("/error_403"));

        return http.build();
    }

    /**
     * Configura la autenticación del usuario utilizando un `UserDetailsService` y
     * un codificador de contraseñas.
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                   .userDetailsService(usuarioDetailsService)
                   .passwordEncoder(passwordEncoder)
                   .and()
                   .build();
    }
}
