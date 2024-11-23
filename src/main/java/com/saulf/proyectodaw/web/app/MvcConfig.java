package com.saulf.proyectodaw.web.app;

import java.nio.file.Paths;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	/**
	 * crea un controlador de recursos estáticos y añade una ubicación para las
	 * imagenes subidas
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		WebMvcConfigurer.super.addResourceHandlers(registry);

		String resourcePath = Paths.get("uploads").toAbsolutePath().toUri().toString();

		registry.addResourceHandler("/uploads/**").addResourceLocations(resourcePath);

	}

	/**
	 * registra como componente Spring nuestro passwordEncoder para encriptar las
	 * contraseñas
	 * 
	 * @return BCryptPasswordEncoder
	 */
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

	/**
	 * añadimos un controlador parametrizable para la vista personalizada en el
	 * error 403
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {

		registry.addViewController("/error_403").setViewName("/error/error_403");
	}

}
