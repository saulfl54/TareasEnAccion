package com.saulf.proyectodaw.web.app.models.service;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * Interfaz para el servicio de manejo de archivos cargados, como imágenes.
 * 
 * Esta interfaz define los métodos necesarios para interactuar con archivos cargados,
 * incluyendo la carga, eliminación, y gestión de archivos dentro del sistema. Los 
 * métodos permiten trabajar con archivos en el contexto de una aplicación web, 
 * como la carga de archivos recibidos a través de un formulario web, su eliminación, 
 * y la obtención de dichos archivos.
 * 
 * @author saulf
 */
public interface ICargarArchivoService {

    /**
     * Carga un archivo desde el sistema de almacenamiento y lo devuelve como un recurso.
     * 
     * @param filename El nombre del archivo que se desea cargar.
     * @return Un recurso que representa el archivo cargado.
     * @throws MalformedURLException Si la URL del archivo no es válida.
     */
    public Resource load(String filename) throws MalformedURLException;

    /**
     * Copia un archivo recibido en una solicitud HTTP y lo almacena en el sistema de archivos.
     * 
     * @param file El archivo que se va a cargar.
     * @return El nombre único asignado al archivo cargado.
     * @throws IOException Si ocurre un error al guardar el archivo en el sistema de archivos.
     */
    public String copy(MultipartFile file) throws IOException;

    /**
     * Elimina un archivo del sistema de almacenamiento.
     * 
     * @param filename El nombre del archivo que se desea eliminar.
     * @return true si el archivo se eliminó correctamente, false si ocurrió algún error.
     */
    public boolean delete(String filename);

    /**
     * Elimina todos los archivos almacenados en el directorio de carga.
     * Este método borra todos los archivos presentes en el sistema de almacenamiento
     * relacionado con la carga de archivos, como imágenes.
     */
    public void deleteAll();

    /**
     * Inicializa el directorio de almacenamiento de archivos, creando el directorio
     * si aún no existe.
     * 
     * @throws IOException Si ocurre un error al crear el directorio.
     */
    public void init() throws IOException;
}
