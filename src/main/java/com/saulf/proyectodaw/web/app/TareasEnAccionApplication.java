package com.saulf.proyectodaw.web.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.saulf.proyectodaw.web.app.models.service.ICargarArchivoService;

@SpringBootApplication
public class TareasEnAccionApplication implements CommandLineRunner{
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	ICargarArchivoService cargarArchivoService;
	public static void main(String[] args) {
		SpringApplication.run(TareasEnAccionApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		   String password = "1234"; // Cambia esta contraseña por la que necesites

	        // Generamos el hash con BCrypt
	        String hashedPassword = passwordEncoder.encode(password);

	        // Imprimimos el hash en consola
	        System.out.println("La contraseña cifrada es: " + hashedPassword);
		cargarArchivoService.deleteAll();
		cargarArchivoService.init();
	}


}



