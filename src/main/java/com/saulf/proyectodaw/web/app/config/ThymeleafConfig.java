package com.saulf.proyectodaw.web.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;
import org.thymeleaf.spring6.SpringTemplateEngine;

import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

/**
 * Clase de configuración para el motor de plantillas Thymeleaf.
 * <p>
 * Esta clase configura Thymeleaf para la aplicación, incluyendo la adición del
 * dialecto de seguridad de Spring (Spring Security) y la resolución de las plantillas
 * que se usarán para las vistas HTML.
 * </p>
 * 
 * @author saulf
 */
@Configuration
public class ThymeleafConfig {

    /**
     * Crea una instancia de {@link SpringTemplateEngine} para gestionar las plantillas de Thymeleaf.
     * <p>
     * Este método configura un motor de plantillas que será utilizado por Spring para renderizar
     * vistas HTML. Se le agrega un dialecto de seguridad para permitir la integración con
     * Spring Security (por ejemplo, expresiones como `sec:authorize` en las vistas).
     * </p>
     * 
     * @return Un {@link SpringTemplateEngine} configurado con el dialecto de Spring Security y
     *         el resolutor de plantillas.
     */
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        
        // Añadir el dialecto de Spring Security al motor de plantillas.
        templateEngine.addDialect(new SpringSecurityDialect());
        
        // Establecer el resolutor de plantillas para la resolución de archivos .html
        templateEngine.setTemplateResolver(templateResolver());
        
        return templateEngine;
    }

    /**
     * Configura el resolutor de plantillas para Thymeleaf.
     * <p>
     * Este método configura un {@link ClassLoaderTemplateResolver} para cargar plantillas desde el
     * directorio `templates/`. Las plantillas deben tener la extensión `.html` y usar codificación
     * UTF-8. Esto le indica a Thymeleaf cómo localizar y procesar las vistas HTML.
     * </p>
     * 
     * @return Un {@link ITemplateResolver} que resuelve las plantillas de Thymeleaf desde el classpath.
     */
    @Bean
    public ITemplateResolver templateResolver() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        
        // Especificar la ubicación de las plantillas
        templateResolver.setPrefix("templates/");
        
        // Especificar la extensión de las plantillas
        templateResolver.setSuffix(".html");
        
        // Establecer el modo de plantillas a HTML
        templateResolver.setTemplateMode("HTML");
        
        // Establecer la codificación de caracteres a UTF-8
        templateResolver.setCharacterEncoding("UTF-8");
        
        return templateResolver;
    }
}
