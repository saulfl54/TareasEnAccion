package com.saulf.proyectodaw.web.app.auth;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Clase que maneja el comportamiento después de un inicio de sesión exitoso.
 * <p>
 * Esta clase extiende {@link SimpleUrlAuthenticationSuccessHandler} para
 * personalizar las acciones a realizar una vez que un usuario se autentica
 * correctamente. Incluye la generación de mensajes flash que se pueden
 * mostrar en la interfaz.
 * 
 * <p>
 * Los mensajes flash son utilizados para proporcionar retroalimentación al
 * usuario sobre el éxito del inicio de sesión.
 * 
 * @author saulf
 */
@Component
public class ExitoLogin extends SimpleUrlAuthenticationSuccessHandler {

    /**
     * Método que se ejecuta tras un inicio de sesión exitoso.
     * <p>
     * Este método personaliza la acción que se realiza después de que el
     * usuario se autentica correctamente. Utiliza un {@link SessionFlashMapManager}
     * para gestionar mensajes flash, que se pueden usar en vistas para informar
     * al usuario sobre el estado del inicio de sesión.
     * 
     * @param request        La solicitud HTTP del cliente.
     * @param response       La respuesta HTTP proporcionada por el servidor.
     * @param authentication Objeto que contiene la información de autenticación del usuario.
     * @throws IOException      Si ocurre un error de entrada/salida durante la operación.
     * @throws ServletException Si ocurre un error relacionado con el servlet.
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        // Administrador de FlashMap para manejar mensajes temporales en la sesión
        SessionFlashMapManager sessionFlashMapManager = new SessionFlashMapManager();

        // Crear un FlashMap para almacenar mensajes flash
        FlashMap flashMap = new FlashMap();

        /*
         * Se utiliza el objeto Authentication para obtener el nombre del usuario que
         * inició sesión. Este nombre se incluye en el mensaje de éxito.
         */
        flashMap.put("success", "Hola " + authentication.getName() + ", su sesión ha sido iniciada!");

        // Guardar el FlashMap en la sesión usando el administrador
        sessionFlashMapManager.saveOutputFlashMap(flashMap, request, response);

        // Llamar al método de la superclase para completar el flujo de éxito de login
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
