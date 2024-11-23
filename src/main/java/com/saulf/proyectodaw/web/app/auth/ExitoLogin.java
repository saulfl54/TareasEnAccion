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
 * Clase que actua después del éxito en el login
 * 
 * @author saulf
 *
 */
@Component
public class ExitoLogin extends SimpleUrlAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		/*
		 * Usamos está forma porque no se puede inyectar como argumento en el método el
		 * RedirectAttributes flash como se puede hacer en el controlador
		 * 
		 */
		SessionFlashMapManager SesionFlashMapManager = new SessionFlashMapManager();

		FlashMap flashMap = new FlashMap();

		// hereda de HashMap. Usamos el objeto authentication que se pasa como argumento
		// para mostrar el nombre
		flashMap.put("success", "Hola " + authentication.getName() + ", su sesión ha sido iniciada!");

		// lo guardamos en el SessionFlashMapManage junto con el request y el response
		SesionFlashMapManager.saveOutputFlashMap(flashMap, request, response);

		super.onAuthenticationSuccess(request, response, authentication);
	}
}
