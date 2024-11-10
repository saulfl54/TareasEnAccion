package com.saulf.proyectodaw.web.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.saulf.proyectodaw.web.app.models.service.ICargarArchivoService;

@SpringBootApplication
public class TareasEnAccionApplication implements CommandLineRunner{

	@Autowired
	ICargarArchivoService cargarArchivoService;
	public static void main(String[] args) {
		SpringApplication.run(TareasEnAccionApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		cargarArchivoService.deleteAll();
		cargarArchivoService.init();
	}


}



