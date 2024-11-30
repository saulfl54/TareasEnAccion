package com.saulf.proyectodaw.web.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controlador principal de la aplicación.
 * <p>
 * Este controlador maneja las solicitudes para las vistas principales de la aplicación, 
 * como la página de inicio. El método `index` se encarga de cargar el modelo con el 
 * atributo "titulo" para mostrar la página principal.
 * </p>
 * 
 * @author saulf
 */
@Controller
public class HomeController {

    /**
     * Maneja las solicitudes GET para las rutas raíz ("/"), "index" y "home".
     * <p>
     * Este método es responsable de devolver la vista principal de la aplicación, 
     * añadiendo el atributo "titulo" al modelo para que esté disponible en la 
     * vista correspondiente. El atributo "titulo" se mostrará en la página de inicio.
     * </p>
     * 
     * @param modelo El objeto {@link Model} que se usa para pasar atributos a la vista.
     * @return El nombre de la vista (en este caso, "index").
     */
    @RequestMapping(value = { "/", "index", "home" }, method = RequestMethod.GET)
    public String index(Model modelo) {
        // Añadir el atributo "titulo" al modelo, que será accesible en la vista
        modelo.addAttribute("titulo", "Tareas en acción");
        
        // Retorna el nombre de la vista que se debe renderizar
        return "index";
    }
}
