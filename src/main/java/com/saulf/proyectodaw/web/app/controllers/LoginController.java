package com.saulf.proyectodaw.web.app.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controlador para la gestión del login de la aplicación.
 * <p>
 * Esta clase maneja las solicitudes para la vista de login, proporcionando 
 * retroalimentación al usuario en caso de error de autenticación o cuando 
 * se cierra sesión correctamente.
 * </p>
 * 
 * @author saulf
 */
@Controller
public class LoginController {

    /**
     * Devuelve la vista de login y maneja los mensajes de error y éxito relacionados
     * con el inicio y cierre de sesión.
     * <p>
     * Este método maneja las solicitudes GET a la ruta "/login". Si el usuario ya 
     * está autenticado, se redirige al usuario a la página principal. Si hay un 
     * error en el inicio de sesión, se muestra un mensaje de error, y si el usuario 
     * ha cerrado sesión, se muestra un mensaje de éxito.
     * </p>
     * 
     * @param error     El parámetro "error" indica si hubo un error en el inicio de sesión.
     * @param logout    El parámetro "logout" indica si el usuario ha cerrado sesión.
     * @param model     El modelo que contiene los atributos a pasar a la vista.
     * @param principal El principal representa al usuario autenticado en la sesión.
     * @param flash     Permite agregar mensajes flash, que se redirigen a la siguiente vista.
     * @return La vista de login (en caso de error o logout) o redirección a la página principal si ya está logueado.
     */
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout, 
                        Model model, 
                        Principal principal,
                        RedirectAttributes flash) {

        // Si el usuario ya está autenticado, redirige a la página principal
        if (principal != null) {
            flash.addFlashAttribute("info", "No puede iniciar sesión nuevamente");
            return "redirect:/"; // Redirige al inicio para evitar múltiples inicios de sesión
        }

        // Si ocurrió un error en el inicio de sesión, muestra el mensaje de error
        if (error != null) {
            model.addAttribute("error", "Nombre de usuario o contraseña incorrecta, por favor vuelva a intentarlo!");
        }

        // Si el usuario ha cerrado sesión, muestra el mensaje de éxito
        if (logout != null) {
            model.addAttribute("success", "Ha cerrado sesión con éxito!");
        }

        // Devuelve la vista de login
        return "login";
    }
}
