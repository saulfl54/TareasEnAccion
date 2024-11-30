package com.saulf.proyectodaw.web.app.utils;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;

/**
 * Clase que representa un paginador para gestionar la paginación de listas en una interfaz.
 * Esta clase se utiliza para construir la lógica de paginación, calcular las páginas a mostrar
 * y crear enlaces a las páginas anteriores, siguientes, primera y última.
 * 
 * @param <T> El tipo de entidad que será paginada.
 */
public class Paginador<T> {

	// CONSTRUCTOR

	/**
	 * Constructor que inicializa el paginador con la URL base y una página de resultados.
	 * Este constructor calcula las páginas visibles en el paginador y las prepara para su visualización.
	 * 
	 * @param url La URL base utilizada para construir los enlaces de paginación.
	 * @param page La página de resultados paginada que contiene los datos y la información de la paginación.
	 */
	public Paginador(String url, Page<T> page) {
		this.url = url;
		this.page = page;
		this.paginas = new ArrayList<PaginaItem>(); // Iniciamos la lista con PaginaItem

		numElementosPorPagina = page.getSize();
		totalPaginas = page.getTotalPages();
		paginaActual = page.getNumber() + 1; // Para que en vez de 0 la primera página sea 1
		int desde, hasta;

		// Lógica para calcular el rango de páginas a mostrar
		if (totalPaginas <= numElementosPorPagina) {
			desde = 1;
			hasta = totalPaginas;
		} else {
			if (paginaActual <= numElementosPorPagina / 2) {
				desde = 1;
				hasta = numElementosPorPagina;
			} else if (paginaActual >= totalPaginas - numElementosPorPagina / 2) {
				desde = totalPaginas - numElementosPorPagina + 1;
				hasta = numElementosPorPagina;
			} else {
				desde = paginaActual - numElementosPorPagina / 2;
				hasta = numElementosPorPagina;
			}
		}

		// Rellenamos la lista de páginas
		for (int i = 0; i < hasta; i++) {
			paginas.add(new PaginaItem(desde + i, paginaActual == desde + i));
		}
	}
	// ----------------Fin constructor-------------------------------

	// Getter para acceder a los valores de la paginación desde la vista.

	/**
	 * Obtiene la URL base para los enlaces de paginación.
	 * 
	 * @return La URL base de la paginación.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Obtiene el número total de páginas disponibles.
	 * 
	 * @return El número total de páginas.
	 */
	public int getTotalPaginas() {
		return totalPaginas;
	}

	/**
	 * Obtiene el número de la página actual.
	 * 
	 * @return El número de la página actual.
	 */
	public int getPaginaActual() {
		return paginaActual;
	}

	/**
	 * Obtiene la lista de las páginas visibles en el paginador.
	 * 
	 * @return Lista de objetos `PaginaItem` que representan las páginas visibles.
	 */
	public List<PaginaItem> getPaginas() {
		return paginas;
	}

	// Métodos para determinar la posición de la página y habilitar/deshabilitar enlaces.

	/**
	 * Verifica si la página actual es la primera página.
	 * 
	 * @return True si la página actual es la primera, false si no lo es.
	 */
	public boolean isFirst() {
		return page.isFirst();
	}

	/**
	 * Verifica si la página actual es la última página.
	 * 
	 * @return True si la página actual es la última, false si no lo es.
	 */
	public boolean isLast() {
		return page.isLast();
	}

	/**
	 * Verifica si hay una página siguiente.
	 * 
	 * @return True si hay una página siguiente, false si no la hay.
	 */
	public boolean isHasNext() {
		return page.hasNext();
	}

	/**
	 * Verifica si hay una página anterior.
	 * 
	 * @return True si hay una página anterior, false si no la hay.
	 */
	public boolean isHasPrevious() {
		return page.hasPrevious();
	}

	// Campos de la clase

	private String url; // URL base para la paginación

	private Page<T> page; // Página de resultados paginada

	private int totalPaginas; // Número total de páginas

	private int numElementosPorPagina; // Número de elementos por página

	private int paginaActual; // Página actual que se está mostrando

	private List<PaginaItem> paginas; // Lista de objetos `PaginaItem` que representan las páginas visibles

}
