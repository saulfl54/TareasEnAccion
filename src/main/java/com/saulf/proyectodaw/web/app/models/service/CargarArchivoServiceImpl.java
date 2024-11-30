package com.saulf.proyectodaw.web.app.models.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * Implementación del servicio para manejar la carga, descarga y eliminación de archivos.
 * 
 * Esta clase proporciona métodos para cargar archivos a un directorio específico, 
 * obtener los archivos cargados, eliminar archivos y limpiar el directorio de carga.
 * Además, la clase asegura que los archivos sean almacenados con nombres únicos 
 * para evitar conflictos con archivos con el mismo nombre.
 * 
 * @author saulf
 */
@Service
public class CargarArchivoServiceImpl implements ICargarArchivoService {

    /**
     * Nombre del directorio donde se almacenan los archivos cargados.
     */
    private final static String DIRECTORIO_UPLOADS = "uploads";

    /**
     * Carga un archivo a partir de su nombre.
     * 
     * @param filename El nombre del archivo que se desea cargar.
     * @return El archivo como recurso.
     * @throws MalformedURLException Si la URL del archivo es incorrecta.
     */
    @Override
    public Resource load(String filename) throws MalformedURLException {
        // Obtener la ruta del archivo
        Path pathFoto = getPath(filename);
        Resource recurso = new UrlResource(pathFoto.toUri());

        // Verificar si el archivo existe y es legible
        if (!recurso.exists() || !recurso.isReadable()) {
            throw new RuntimeException("Error: no se puede cargar : " + pathFoto.toString());
        }
        return recurso;
    }

    /**
     * Copia un archivo recibido desde una solicitud HTTP y lo almacena en el directorio de carga.
     * El archivo se almacena con un nombre único generado a partir de un UUID.
     * 
     * @param file El archivo a cargar.
     * @return El nombre único asignado al archivo.
     * @throws IOException Si ocurre un error al guardar el archivo.
     */
    @Override
    public String copy(MultipartFile file) throws IOException {
        // Generar un nombre único para el archivo
        String nombreUnicoFile = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path rootPath = getPath(nombreUnicoFile);

        // Copiar el archivo a la ruta de destino
        Files.copy(file.getInputStream(), rootPath);

        return nombreUnicoFile;
    }

    /**
     * Elimina un archivo del sistema de archivos.
     * 
     * @param filename El nombre del archivo a eliminar.
     * @return true si el archivo fue eliminado correctamente, false en caso contrario.
     */
    @Override
    public boolean delete(String filename) {
        // Obtener la ruta del archivo
        File file = getPath(filename).toFile();

        // Verificar si el archivo existe y si se puede leer
        if (file.exists() && file.canRead()) {
            if (file.delete()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Obtiene la ruta completa de un archivo dado su nombre.
     * 
     * @param filename El nombre del archivo.
     * @return La ruta completa del archivo.
     */
    public Path getPath(String filename) {
        return Paths.get(DIRECTORIO_UPLOADS).resolve(filename).toAbsolutePath();
    }

    /**
     * Elimina todos los archivos en el directorio de carga.
     * Esta operación borra el directorio "uploads" y todo su contenido.
     */
    @Override
    public void deleteAll() {
        // Borrar recursivamente todo el contenido del directorio de carga
        FileSystemUtils.deleteRecursively(Paths.get(DIRECTORIO_UPLOADS).toFile());
    }

    /**
     * Crea nuevamente el directorio de carga "uploads".
     * Este método es útil para inicializar el entorno de almacenamiento de archivos.
     * 
     * @throws IOException Si ocurre un error al crear el directorio.
     */
    @Override
    public void init() throws IOException {
        // Crear el directorio de carga si no existe
        Files.createDirectory(Paths.get(DIRECTORIO_UPLOADS));
    }
}
