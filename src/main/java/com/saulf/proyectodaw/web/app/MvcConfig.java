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
 * <p>
 * Proporciona configuraciones relacionadas con Spring MVC, como el manejo de
 * recursos estáticos, la encriptación de contraseñas y la configuración de
 * controladores de vistas personalizadas para errores.
 * </p>
 * 
 * @author saulf
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * Configura el controlador para el acceso a recursos estáticos.
     * <p>
     * Este método agrega un controlador para manejar archivos en el directorio
     * "uploads", permitiendo que los archivos sean accesibles desde la URL
     * "/uploads/**".
     * </p>
     * 
     * @param registry el registro de controladores de recursos donde se agrega
     *                 la configuración personalizada.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // Llama al método base para mantener la configuración predeterminada.
        WebMvcConfigurer.super.addResourceHandlers(registry);

        // Ruta absoluta a la carpeta "uploads" donde se guardan los archivos subidos.
        String resourcePath = Paths.get("uploads").toAbsolutePath().toUri().toString();

        // Registra la ubicación de los recursos estáticos en el registro.
        registry.addResourceHandler("/uploads/**").addResourceLocations(resourcePath);
    }

    /**
     * Declara un bean para la encriptación de contraseñas.
     * <p>
     * Este método expone una instancia de {@link BCryptPasswordEncoder} como un
     * bean de Spring, que será utilizado en la aplicación para encriptar las
     * contraseñas de forma segura usando el algoritmo bcrypt.
     * </p>
     * 
     * @return una instancia de {@link BCryptPasswordEncoder}.
     */
    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {

        // Retorna una nueva instancia del codificador BCrypt.
        return new BCryptPasswordEncoder();
    }

    /**
     * Configura un controlador de vistas personalizadas.
     * <p>
     * Este método asigna una vista específica para manejar errores de acceso no
     * autorizado (403), redirigiendo al usuario a una página personalizada
     * "/error/error_403".
     * </p>
     * 
     * @param registry el registro de controladores de vistas donde se agrega la
     *                 configuración para el error 403.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        // Registra la vista personalizada para errores de acceso no autorizado (403).
        registry.addViewController("/error_403").setViewName("/error/error_403");
    }
}
