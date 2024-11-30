package com.saulf.proyectodaw.web.app.utils;

/**
 * Clase que representa una página dentro del paginador.
 * Esta clase almacena el número de la página y si esa página es la actual en la paginación.
 * 
 * @author saulf
 */
public class PaginaItem {

	private int numero;  // Número de la página.
	private boolean actual;  // Indica si la página es la actual.

	/**
	 * Constructor para crear una instancia de PaginaItem.
	 * 
	 * @param numero El número de la página.
	 * @param actual Un valor booleano que indica si la página es la actual.
	 */
	public PaginaItem(int numero, boolean actual) {
		this.numero = numero;
		this.actual = actual;
	}

	/**
	 * Obtiene el número de la página.
	 * 
	 * @return El número de la página.
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * Verifica si la página es la actual en la paginación.
	 * 
	 * @return True si la página es la actual, false si no lo es.
	 */
	public boolean isActual() {
		return actual;
	}
}
