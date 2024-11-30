package com.saulf.proyectodaw.web.app;

import java.nio.file.Paths;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Clase de configuración para la aplicación web.
 * 
 * Esta clase configura diversos aspectos relacionados con Spring MVC,
 * incluyendo la gestión de recursos estáticos, la encriptación de contraseñas y
 * el manejo de vistas personalizadas para errores específicos.
 * 
 * @author saulf
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

	/**
	 * Configura el controlador para los recursos estáticos y añade una ubicación
	 * para las imágenes subidas por los usuarios.
	 * 
	 * Se especifica que los archivos en el directorio "uploads" serán accesibles a
	 * través de la URL "/uploads/**".
	 * 
	 * @param registry El registro de controladores de recursos para agregar el
	 *                 controlador de recursos estáticos.
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		// Llama al método base de WebMvcConfigurer para mantener otros controladores.
		WebMvcConfigurer.super.addResourceHandlers(registry);

		// Definir la ruta absoluta de las imágenes subidas.
		String resourcePath = Paths.get("uploads").toAbsolutePath().toUri().toString();

		// Agregar la ruta para las imágenes subidas y registrarla en el registry.
		registry.addResourceHandler("/uploads/**").addResourceLocations(resourcePath);
	}

	/**
	 * Registra el componente BCryptPasswordEncoder como un bean de Spring para la
	 * encriptación de contraseñas.
	 * 
	 * Este bean se utilizará para encriptar las contraseñas de los usuarios de
	 * manera segura utilizando el algoritmo bcrypt.
	 * 
	 * @return Una instancia del {@link BCryptPasswordEncoder} para encriptar
	 *         contraseñas.
	 */
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {

		// Retorna una nueva instancia de BCryptPasswordEncoder.
		return new BCryptPasswordEncoder();
	}

	/**
	 * Registra un controlador de vistas personalizadas para el error 403.
	 * 
	 * Se asigna la vista "/error/error_403" a la URL "/error_403", lo que permite
	 * que el sistema redirija a esta vista cuando se produzca un error de acceso no
	 * autorizado (403).
	 * 
	 * @param registry El registro de controladores de vistas para agregar el
	 *                 controlador de error 403.
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {

		// Registrar la vista personalizada para el error 403.
		registry.addViewController("/error_403").setViewName("/error/error_403");
	}
}
