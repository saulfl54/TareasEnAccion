package com.saulf.proyectodaw.web.app.models.service;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * interfaz service para tratar las imagenes subidas
 * 
 * @author saulf
 *
 */
public interface ICargarArchivoService {

	public Resource load(String filename) throws MalformedURLException;

	public String copy(MultipartFile file) throws IOException;

	public boolean delete(String filename);

	public void deleteAll();

	public void init() throws IOException;
}