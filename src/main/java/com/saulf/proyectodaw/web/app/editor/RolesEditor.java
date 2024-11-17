package com.saulf.proyectodaw.web.app.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saulf.proyectodaw.web.app.models.service.IRoleService;



/**
 * Clase propertyEditor de Role
 * @author saulf
 *
 */
@Component
public class RolesEditor extends PropertyEditorSupport{

	@Autowired
	private IRoleService service;
	
	/**
	 * establece un rol para el usuario 
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		try {
			Long id =Long.parseLong(text);
			setValue(service.findOne(id));
		} catch(NumberFormatException e) {
			setValue(null);
		}
	}

}
