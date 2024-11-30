package com.saulf.proyectodaw.web.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.saulf.proyectodaw.web.app.models.service.ICargarArchivoService;

/**
 * Clase principal de la aplicación Tareas en Acción.
 * <p>
 * Esta clase inicializa el contexto de Spring Boot y configura tareas iniciales
 * para la aplicación, como la limpieza y configuración de archivos en el sistema.
 * 
 * <p>
 * Se implementa la interfaz {@link CommandLineRunner} para ejecutar código adicional
 * después de que el contexto de la aplicación haya sido cargado completamente.
 * 
 * @author saulf
 */
@SpringBootApplication
public class TareasEnAccionApplication implements CommandLineRunner {

    /**
     * Bean para manejar la encriptación de contraseñas.
     * Utilizado en la gestión de credenciales de los usuarios.
     */
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Servicio para gestionar operaciones relacionadas con la carga de archivos.
     * Este servicio se encarga de inicializar y limpiar el directorio de archivos
     * al iniciar la aplicación.
     */
    @Autowired
    ICargarArchivoService cargarArchivoService;

    /**
     * Método principal de la aplicación.
     * <p>
     * Este método arranca la aplicación Spring Boot utilizando la clase
     * {@link SpringApplication}.
     * 
     * @param args argumentos de línea de comandos pasados al ejecutar la aplicación.
     */
    public static void main(String[] args) {
        SpringApplication.run(TareasEnAccionApplication.class, args);
    }

    /**
     * Tareas a ejecutar tras la inicialización de la aplicación.
     * <p>
     * Este método limpia el sistema de archivos eliminando todos los archivos existentes
     * y luego inicializa la estructura necesaria para el correcto funcionamiento
     * del sistema de gestión de tareas.
     * 
     * @param args argumentos de línea de comandos (no utilizados en este caso).
     * @throws Exception si ocurre algún problema al inicializar o limpiar los archivos.
     */
    @Override
    public void run(String... args) throws Exception {
        // Elimina todos los archivos existentes en el sistema de gestión de archivos.
        cargarArchivoService.deleteAll();

        // Inicializa el sistema de gestión de archivos, creando los directorios necesarios.
        cargarArchivoService.init();
    }
}
