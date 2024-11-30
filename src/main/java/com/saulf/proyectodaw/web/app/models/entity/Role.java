package com.saulf.proyectodaw.web.app.models.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Representa un rol dentro del sistema. Esta entidad se mapea a la tabla "roles" 
 * en la base de datos y contiene la información asociada a un rol, como su nombre 
 * y el nombre del rol en el sistema.
 * 
 * Un rol se asocia a los usuarios del sistema para asignarles permisos y funciones.
 * 
 * @author saulf
 */
@Entity
@Table(name = "roles")
public class Role implements Serializable {

    /**
     * Identificador único del rol.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre del rol, utilizado para describir el rol en el sistema.
     */
    private String nombre;

    /**
     * Representación del rol en el sistema. Este campo puede estar asociado a 
     * una clave interna del sistema para la gestión de permisos.
     */
    private String role;

    /**
     * Constructor por defecto de la clase Role.
     */
    public Role() {
    }

    /**
     * Constructor para inicializar un rol con los parámetros dados.
     * 
     * @param id    El identificador único del rol.
     * @param nombre El nombre del rol.
     * @param role  El nombre del rol en el sistema.
     */
    public Role(Long id, String nombre, String role) {
        this.id = id;
        this.nombre = nombre;
        this.role = role;
    }

    /**
     * Obtiene el identificador del rol.
     * 
     * @return El identificador único del rol.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador del rol.
     * 
     * @param id El identificador del rol.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del rol.
     * 
     * @return El nombre del rol.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del rol.
     * 
     * @param nombre El nombre del rol.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre del rol en el sistema.
     * 
     * @return El nombre del rol en el sistema.
     */
    public String getRole() {
        return role;
    }

    /**
     * Establece el nombre del rol en el sistema.
     * 
     * @param role El nombre del rol en el sistema.
     */
    public void setRole(String role) {
        this.role = role;
    }

    private static final long serialVersionUID = 1L;
}
