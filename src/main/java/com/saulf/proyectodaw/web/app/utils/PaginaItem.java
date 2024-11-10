package com.saulf.proyectodaw.web.app.utils;

/**
 * clase que representa una página
 * @author saulf
 *
 */
public class PaginaItem {

	private int numero;//numero de pagina
	private boolean actual;//si es actual o no.

	public PaginaItem(int numero, boolean actual) {
		this.numero = numero;
		this.actual = actual;
	}

	public int getNumero() {
		return numero;
	}

	public boolean isActual() {
		return actual;
	}

}
