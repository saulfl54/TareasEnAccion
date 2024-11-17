package com.saulf.proyectodaw.web.app.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.saulf.proyectodaw.web.app.models.entity.Comentario;
import com.saulf.proyectodaw.web.app.models.entity.Tarea;
import com.saulf.proyectodaw.web.app.models.entity.Usuario;
import com.saulf.proyectodaw.web.app.models.service.ICargarArchivoService;
import com.saulf.proyectodaw.web.app.models.service.IUsuarioService;
import com.saulf.proyectodaw.web.app.utils.Paginador;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/tarea")
@SessionAttributes("tarea")
public class TareaController {
	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	private ICargarArchivoService cargarArchivoService;
	
	/**
	 * 
	 * devuelve la lista con todas las tareas
	 * @param model
	 * @return vista lista
	 */

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {

		List<Tarea> tareas = usuarioService.findAllOrderByCreateAtDes();

		model.addAttribute("titulo", "Tareas");

		model.addAttribute("tareas", tareas);

		return "tarea/listar";
	}
	/**
	 * devuelve el formulario para crear una tarea
	 * @param usuarioId
	 * @param model
	 * @param flash
	 * @return vista form
	 */
	@GetMapping("/form/{usuarioId}")
	public String crear(@PathVariable(value = "usuarioId") Long usuarioId, Map<String, Object> model,
			RedirectAttributes flash) {

		Usuario usuario = usuarioService.findOne(usuarioId);

		if (usuario == null) {
			flash.addFlashAttribute("error", "El usuario no existe en la base de datos");
			return "redirect:/listar";
		}

		Tarea tarea = new Tarea();
		tarea.setUsuario(usuario);

		model.put("tarea", tarea);
		model.put("titulo", "Crear Tarea");

		return "tarea/form";
	}

	/**
	 * crea una tarea
	 * 
	 * @param tarea
	 * @param result
	 * @param model
	 * @param flash
	 * @param status
	 * @param file
	 * @return vista listar
	 */
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Tarea tarea, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status, @RequestParam("file") MultipartFile file) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Crear Tarea");
			return "tarea/form";
		}

		if (!file.isEmpty()) {

			if (tarea.getId() != null && tarea.getId() > 0 && tarea.getImagen() != null
					&& tarea.getImagen().length() > 0) {

				cargarArchivoService.delete(tarea.getImagen());
			}

			String nombreUnicoFile = null;
			try {
				nombreUnicoFile = cargarArchivoService.copy(file);
			} catch (IOException e) {

				e.printStackTrace();
			}

			flash.addFlashAttribute("info", "Se ha subido con éxito '" + nombreUnicoFile + "'");

			tarea.setImagen(nombreUnicoFile);
		}

		String mensajeFlash = (tarea.getId() != null) ? "tarea editada con éxito!"
				: "Tarea creada con éxito!";

		usuarioService.saveTarea(tarea);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/tarea/listar";
	}
	
	/**
	 * devuelve los detalles de una tarea
	 * @param id
	 * @param model
	 * @param flash
	 * @return vista ver
	 */

	@GetMapping("/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		Tarea tarea = usuarioService.findTareaById(id);

		if (tarea == null) {
			flash.addFlashAttribute("error", "Tarea inexistente en la base de datos!");
			return "redirect:/listar";
		}

		model.addAttribute("tarea", tarea);
		model.addAttribute("nombrePerfil", tarea.getUsuario().getUsername());
		model.addAttribute("titulo", tarea.getTitulo());

		return "tarea/ver";
	}
	
	/**
	 * elimina una tarea
	 * @param id
	 * @param flash
	 * @return vista listar
	 */

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		Tarea tarea = usuarioService.findTareaById(id);

		if (tarea != null) {
			usuarioService.deleteTarea(id);
			flash.addFlashAttribute("success", "Tarea eliminada con éxito!");
			return "redirect:/usuario/ver/" + tarea.getUsuario().getId();
		}
		flash.addFlashAttribute("error", "No se pudo eliminar, no existe en la base de datos!");

		return "redirect:/listar";
	}
	/**
	 * crea un comentario en una tarea
	 * @param comentario
	 * @param tareaId
	 * @param model
	 * @param flash
	 * @return la vista ver
	 */

	@PostMapping("/comentar/{tareaId}")
	public String comentar(Comentario comentario, @PathVariable(value = "tareaId") Long tareaId,
			Map<String, Object> model, RedirectAttributes flash) {

		Tarea tarea = usuarioService.findTareaById(tareaId);

		if (tarea == null) {
			flash.addFlashAttribute("error", "La tarea no existe en la base de datos");
			return "redirect:/listar";
		}

		// -----------------OBTENER USERNAME-----------
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

		Usuario usuario = usuarioService.findByUsername(username);
		usuarioService.saveComentario(comentario, usuario, tarea);
		flash.addFlashAttribute("success", "Comentario realizado con éxito!");

		model.put("tarea", tarea);
		model.put("titulo", tarea.getTitulo());

		return "redirect:/tarea/ver/{tareaId}";
	}
	/**
	 * elimina un comentario de la tarea
	 * @param idComentario
	 * @param idTarea
	 * @param flash
	 * @return la vista listar
	 */

	@GetMapping("/eliminarComentario/{idComentario}/{idTarea}")
	public String eliminarComentario(@PathVariable(value = "idComentario") Long idComentario,
			@PathVariable(value = "idTarea") Long idTarea, RedirectAttributes flash) {

		Comentario comentario = usuarioService.findComentarioById(idComentario);
		Tarea tarea = usuarioService.findTareaById(idTarea);

		if ((tarea != null && comentario != null) || tarea != null || comentario != null) {
			usuarioService.deleteComentario(idComentario);
			flash.addFlashAttribute("success", "Comentario eliminado con éxito!");
			return "redirect:/tarea/ver/" + tarea.getId();
		}
		flash.addFlashAttribute("error", "No se pudo eliminar, no existe en la base de datos!");

		return "redirect:/usuario/listar";
	}

	
}