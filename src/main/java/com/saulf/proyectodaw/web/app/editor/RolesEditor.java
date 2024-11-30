package com.saulf.proyectodaw.web.app.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saulf.proyectodaw.web.app.models.service.IRoleService;

/**
 * Clase que extiende PropertyEditorSupport para convertir un texto en un objeto de tipo Role.
 * Se utiliza para la conversión de cadenas de texto a objetos de tipo Role en el contexto de Spring MVC.
 * 
 * @author saulf
 */
@Component
public class RolesEditor extends PropertyEditorSupport {

    @Autowired
    private IRoleService service;

    /**
     * Convierte una cadena de texto que representa el ID de un rol en un objeto de tipo Role.
     * 
     * @param text El texto que representa el ID de un rol.
     * @throws IllegalArgumentException Si el texto no puede ser convertido a un ID válido.
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            // Intentamos convertir el texto a un ID de tipo Long
            Long id = Long.parseLong(text);
            // Buscamos el rol con el ID y lo asignamos
            setValue(service.findOne(id));
        } catch (NumberFormatException e) {
            // Si ocurre un error en la conversión, asignamos null
            setValue(null);
        }
    }
}
